package com.u2u.ibms.web.order.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.common.component.dingding.DingDingAuthUtil;
import com.u2u.common.component.identificationcard.IdentityResponse;
import com.u2u.common.component.identificationcard.IdentityUtils;
import com.u2u.framework.base.BaseService;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.AutoSubOrderService;
import com.u2u.ibms.common.Config;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.CustomerName;
import com.u2u.ibms.common.ImageWaterMark;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.IdentifyCertification;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Project;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.common.mapper.IdentifyCertificationMapper;
import com.u2u.ibms.common.mapper.LocationMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.ProjectMapper;
import com.u2u.ibms.common.mapper.RentCompanyMapper;
import com.u2u.ibms.common.mapper.RentPersonMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.common.util.CommonIdGenerator;
import com.u2u.ibms.web.order.condition.OrderCondition;
import com.u2u.ibms.web.project.bean.OrderStatistics;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OrderService extends BaseService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private RentPersonMapper rentPersonMapper;

	@Autowired
	private RentCompanyMapper rentCompanyMapper;

	@Autowired
	private SubOrderMapper subOrderMapper;

	@Autowired
	private ComboMapper comboMapper;

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private AuthorizeService authorizeService;

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private IdentifyCertificationMapper identifyCertificationMapper;

	@Autowired
	private AutoSubOrderService autoSubOrderService;

	public boolean validateIsDenied(Order order) {
		if (order.getStatus().trim()
				.equalsIgnoreCase(Constants.ORDER_000_ABANDONED)
				|| order.getStatus().trim()
						.equalsIgnoreCase(Constants.ORDER_001_CREDIT_ABANDONED)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateProjectIsDenied(Order order) {
		if (order.getStatus().trim()
				.equalsIgnoreCase(Constants.ORDER_000_ABANDONED)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateIsCreditMultichecked(Order order) {
		if (order.getStatus().trim().equalsIgnoreCase(Constants.ORDER_1_APPLY)
				|| order.getStatus().trim()
						.equalsIgnoreCase(Constants.ORDER_2_ORDER_HANDLE)
				|| order.getStatus().trim()
						.equalsIgnoreCase(Constants.ORDER_3_PROJECT_HANDLE)
				|| order.getStatus().trim()
						.equalsIgnoreCase(Constants.ORDER_4_PROJECT_CHECK)
				|| order.getStatus().trim()
						.equalsIgnoreCase(Constants.ORDER_5_CREDIT_CHECK)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Order> getAll(RowBounds rb, OrderCondition condition,
			Boolean assigned) {
		//System.out.println("condition.getStartDate: " + getStartDate(condition) + 
		//		",   getEndDate(condition):" + getEndDate(condition));
		List<Order> orders = orderMapper.getAll(rb,
				getStringCondition(condition.getCode()),
				getIntegerCondition(condition.getCreateSource()),
				getIntegerCondition(condition.getOperatorId()), assigned, null,
				getBooleanCondition(condition.getDelStatus()) == null ? false
						: getBooleanCondition(condition.getDelStatus()),
				getStartDate(condition), 
				getEndDate(condition),
				getStringCondition(condition.getStatus()),
				getStringCondition(condition.getRentType()),
				getStringCondition(condition.getCustomerName()),
				getStringCondition(condition.getOperator()),
				getStringCondition(condition.getAssetProvinceId())
				);
		for (final Order order : orders) {
			this.convertOrder(order);
		}
		return orders;
	}

	public Order getById(int id) {
		Order order = orderMapper.getById(id);
		if(order == null){
			return order;
		}else{
			return convertOrder(order);
		}
	}

	public OrderStatistics getOrderStatistics(int orderId) {
		OrderStatistics statistics = new OrderStatistics();
		Order order = orderMapper.getById(orderId);
		statistics.setDate(order.getRentDate());

		List<SubOrder> subOrders = subOrderMapper.getByOrderId(new RowBounds(),
				orderId);
		for (SubOrder sub : subOrders) {
			statistics.setAmount(statistics.getAmount() + sub.getAmount());
			statistics.setDeposit(statistics.getDeposit() + sub.getDeposit());
			statistics.setRentAmount(statistics.getRentAmount()
					+ comboMapper.getById(sub.getComboId()).getAmount());
		}

		try {
			if (order.getRentType() == 0) {
				statistics.setRentSumAmount(0);
			} else {
				statistics.setRentSumAmount(statistics.getRentAmount()
						* statistics.getDate());
			}
			float temp = (statistics.getDeposit() / statistics.getAmount()) * 100;
			DecimalFormat decimalFormat = new DecimalFormat(".00");
			statistics.setDepositRatio(Float.parseFloat(decimalFormat
					.format(temp)));
		} catch (Exception e) {
			statistics.setDepositRatio(0);
		}
		return statistics;
	}

	public Order convertOrder(final Order order) {
		
		order.setStartDatetime(DateUtil.timestamp2String(order.getStartDate(),
				DateUtil.PATTERN_DATE));
		order.setEndDatetime(DateUtil.timestamp2String(order.getEndDate(),
				DateUtil.PATTERN_DATE));
		Province province = locationMapper.getProvinceById(order
				.getProvinceId());
		if (province != null) {
			order.setProvince(province.getName());
		} else {
			order.setProvince("");
		}
		City city = locationMapper.getCityById(order.getCityId());
		if (city != null) {
			order.setCity(city.getName());
		} else {
			order.setCity("");
		}

		try {

			User user = authorizeService.getUser(order.getManagerId());
			if (user != null) {
				order.setManager(user);
			} else {
				order.setManager(new User());
			}
		} catch (Exception e) {
			order.setManager(new User());
		}

		if (order.getRentPersonType() == 0) {
			RentPersonInfo person = rentPersonMapper.getById(order
					.getRentPersonId());
			if (person != null
					&& locationMapper.getProvinceById(person
							.getPersonProvinceId()) != null) {
				person.setPersonProvince(locationMapper.getProvinceById(
						person.getPersonProvinceId()).getName());
			} else {
				person.setPersonProvince("");
			}

			if (person != null
					&& locationMapper.getCityById(person.getPersonCityId()) != null) {
				person.setPersonCity(locationMapper.getCityById(
						person.getPersonCityId()).getName());
			} else {
				person.setPersonCity("");
			}
			order.setRentPersonInfo(person);
		} else {
			RentCompanyInfo company = rentCompanyMapper.getById(order
					.getRentCompanyId());
			if (company != null
					&& locationMapper.getProvinceById(company
							.getCompanyProvinceId()) != null) {
				company.setCompanyProvince(locationMapper.getProvinceById(
						company.getCompanyProvinceId()).getName());
			} else {
				company.setCompanyProvince("");
			}
			if (company != null
					&& locationMapper.getCityById(company.getCompanyCityId()) != null) {
				company.setCompanyCity(locationMapper.getCityById(
						company.getCompanyCityId()).getName());
			} else {
				company.setCompanyCity("");
			}
			order.setRentCompanyInfo(company);
		}
		order.setAssetFullAdress(order.getProvince() + order.getCity()
				+ order.getDetailLocation());
		return order;
	}

	private String getStartComma(String src) {
		if (StringUtils.isNotEmpty(src)) {
			return src.substring(0, src.indexOf(","));
		} else {
			return null;
		}
	}

	private String getEndComma(String src) {
		if (StringUtils.isNotEmpty(src)) {
			return src.substring(src.indexOf(",") + 1);
		} else {
			return null;
		}
	}

	public void insert(HttpServletRequest request, Order order,
			RentCompanyInfo rentCompanyInfo, RentPersonInfo rentPersonInfo) {

		if (order.getRentPersonType() == 0) {
			rentPersonInfo
					.setName(this.getStartComma(rentPersonInfo.getName()));
			rentPersonInfo.setAddress(this.getStartComma(rentPersonInfo
					.getAddress()));
			rentPersonInfo.setPostcode(this.getStartComma(rentPersonInfo
					.getPostcode()));
			rentPersonInfo.setCreateDate(DateUtil.currentTimestamp());
			rentPersonInfo.setOperateDate(DateUtil.currentTimestamp());
			rentPersonMapper.insert(rentPersonInfo);
			order.setRentPersonId(rentPersonInfo.getId());
		} else {
			rentCompanyInfo
					.setName(this.getEndComma(rentCompanyInfo.getName()));
			rentCompanyInfo.setAddress(this.getEndComma(rentCompanyInfo
					.getName()));
			rentCompanyInfo.setPostcode(this.getEndComma(rentCompanyInfo
					.getPostcode()));
			rentCompanyInfo.setCreateDate(DateUtil.currentTimestamp());
			rentCompanyInfo.setOperateDate(DateUtil.currentTimestamp());
			//解决jsp页面input标签name同名问题
			rentCompanyInfo.setIdCardFrontImg(rentCompanyInfo.getC_idCardFrontImg());
			rentCompanyInfo.setIdCardBackImg(rentCompanyInfo.getC_idCardBackImg());
			rentCompanyInfo.setIdCardHandImg(rentCompanyInfo.getC_idCardHandImg());
			rentCompanyMapper.insert(rentCompanyInfo);
			order.setRentCompanyId(rentCompanyInfo.getId());
		}

		order.setCode(CommonIdGenerator.generateOrderId());
		order.setUserId(0);
		order.setDeposit(0);
		order.setLeftDeposit(0);
		order.setCreateSource(1);
		order.setAssigned(false);
		order.setStartDate(DateUtil.string2Timestamp(order.getStartDatetime(),
				DateUtil.PATTERN_DATE));
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(order.getStartDate().getTime());
		calendar.add(Calendar.MONTH, order.getRentDate());
		String endDate = DateUtil.date2String(calendar.getTime(),
				DateUtil.PATTERN_DATE);
		order.setEndDate(DateUtil.string2Timestamp(endDate,
				DateUtil.PATTERN_DATE));
		order.setStatus(Constants.ORDER_1_APPLY);
		order.setCreateDate(DateUtil.currentTimestamp());
		order.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.insert(order);

		List<String> assets = new ArrayList<String>();
		Enumeration<String> enumes = request.getParameterNames();

		while (enumes.hasMoreElements()) {
			String current = enumes.nextElement();
			if (current.startsWith("combo_")) {
				assets.add(current.replace("combo_", ""));
			}
		}
		float deposit = 0f;
		for (String index : assets) {
			SubOrder subOrder = new SubOrder();
			subOrder.setOrderId(order.getId());
			subOrder.setComboId(Integer.valueOf(request.getParameter("combo_"
					+ index)));
			subOrder.setAssetTypeId(Integer.valueOf(request
					.getParameter("assetType_" + index)));
			subOrder.setCount(Integer.valueOf(request.getParameter("count_"
					+ index)));
			try {
				subOrder.setDeposit(Float.valueOf(request
						.getParameter("deposit_" + index)));
			} catch (Exception e) {
				subOrder.setDeposit(0);
			}
			try {
				subOrder.setAmount(Float.valueOf(request.getParameter("amount_"
						+ index)));
			} catch (Exception e) {
				subOrder.setAmount(0);
			}
			subOrder.setCreateDate(DateUtil.currentTimestamp());
			subOrder.setOperateDate(DateUtil.currentTimestamp());
			subOrderMapper.insert(subOrder);
			deposit = deposit + subOrder.getDeposit();
		}
		Order exist = orderMapper.getById(order.getId());
		exist.setDeposit(deposit);
		orderMapper.update(exist);

		autoSubOrderService.seperate(exist);
	}

	public List<SubOrder> getSubOrders(RowBounds rb, int orderId) {
		final List<SubOrder> subOrders = subOrderMapper.getByOrderId(rb,
				orderId);
		for (final SubOrder subOrder : subOrders) {
			subOrder.setCombo(comboMapper.getById(subOrder.getComboId())
					.getName());
			
			AssetType assetType = assetTypeMapper.getById(subOrder
					.getAssetTypeId());
			subOrder.setBrand(assetType.getBrand());
			subOrder.setAssetType(assetType.getModel());
		}
		return subOrders;
	}

	public void update(Order order) {
		Order exist = orderMapper.getById(order.getId());
		//System.out.println("exist.getCustomerName(): " + exist.getCustomerName());
		exist.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(exist);
	}

	public void sepepate(Order order) throws ServiceAuthorizeException {
		Order exist = orderMapper.getById(order.getId());
		exist.setOperatorId(order.getOperatorId());

		User user = authorizeService.getUser(exist.getOperatorId());

		// exist.setOperator(user.getRealname());
		// exist.setOperatorMobile(user.getMobile());

		exist.setManagerId(exist.getOperatorId());
		exist.setAssigned(true);
		exist.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(exist);

		DingDingAuthUtil.send(user);
	}

	public void verify(Order order) {
		Order exist = orderMapper.getById(order.getId());
		exist.setStatus(Constants.ORDER_2_ORDER_HANDLE);
		exist.setFeedStatus(order.isFeedStatus());
		exist.setFeedback(order.getFeedback());
		exist.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(exist);

		if (order.isFeedStatus()) {
			Project project = new Project();
			project.setProjectId(CommonIdGenerator.generateProjectId());
			project.setOrderId(order.getId());
			// project.setOperator(operator);
			// project.setOperatorMobile();
			project.setCreateDate(DateUtil.currentTimestamp());
			project.setOperateDate(DateUtil.currentTimestamp());
			projectMapper.insert(project);
		}
		DingDingAuthUtil.send(Constants.DINGDING_3_PROJECT_HANDLE);
	}

	public void delete(int id) {
		Order exist = orderMapper.getById(id);
		exist.setDelStatus(true);
		exist.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(exist);
		// Order order = orderMapper.getById(id);
		//
		// orderMapper.delete(order.getId());
		// if (order.getRentPersonType() == 0) {
		// rentPersonMapper.delete(order.getRentPersonId());
		// } else {
		// rentCompanyMapper.delete(order.getRentCompanyId());
		// }
		// subOrderMapper.deleteByOrderId(order.getId());
	}

	public synchronized String identifyCertification(String id, String idCard)
			throws Exception {
		Order order = this.getById(getIntegerCondition(id));
		String name = null;
		String idCardImg = null;
		if (order.getRentPersonType() == 0) {
			name = order.getRentPersonInfo().getName();
			idCardImg = order.getRentPersonInfo().getIdCardFrontImg();
		} else {
			name = order.getRentCompanyInfo().getLegalName();
			idCardImg = order.getRentCompanyInfo().getIdCardFrontImg();
		}
		idCardImg = Config.webUrl + idCardImg;
		
		IdentifyCertification identifyCertification = identifyCertificationMapper
				.getByNameAndIdcard(name, idCard);

		String result = null;
		if (identifyCertification == null) {
			try {
			
				String ret = IdentityUtils.identityCard(name, idCard);

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);
				IdentityResponse response = objectMapper.readValue(ret,
						IdentityResponse.class);
				if (response.getError_code() != 0) {
					throw new RuntimeException(response.getReason());
				}
				if (response.getResult().getRes() != 1) {
					throw new RuntimeException("姓名与证件号码不匹配！");
				}

				IdentifyCertification identify = new IdentifyCertification();
				identify.setName(name);
				identify.setIdCard(idCard);		
				//identify.setIdImg(response.getResult().getHeadimg());	//聚合返回头像功能失效，SUNZHE, 2017-05-23
				//System.out.println(idCardImg);
				
				String markedIdImg = ImageWaterMark.addTxtMark(idCardImg, "Passed! ", 30);
				identify.setIdImg(markedIdImg);
				identify.setCreateDate(DateUtil.currentTimestamp());
				identify.setOperateDate(DateUtil.currentTimestamp());
				identifyCertificationMapper.insert(identify);

				//result = response.getResult().getHeadimg();		//聚合返回头像功能失效，SUNZHE, 2017-05-23
				result = markedIdImg;
			} catch (Exception e) {
				throw e;
			}
		} else {
			result = identifyCertification.getIdImg();
		}
		return result;
	}

	public void updateIdentify(String id, String identify) {
		Order order = orderMapper.getById(getIntegerCondition(id));
		order.setIdCard(identify);
		orderMapper.update(order);
	}

	public List<CustomerName> getCustomerNames(RowBounds rowBounds) {
		return orderMapper.getCustomerNames(rowBounds);
	}

	
	public void modify(Order order, RentCompanyInfo rentCompanyInfo, RentPersonInfo rentPersonInfo) {
		//HttpServletRequest request
		Order exist = orderMapper.getById(order.getId());
		
		if(exist == null){
			return;
		}else{
			exist = convertOrder(exist);
		}
		
		RentPersonInfo existRentPersonInfo = exist.getRentPersonInfo();
		RentCompanyInfo existRentCompanyInfo = exist.getRentCompanyInfo();
	
			if (exist.getRentPersonType() == 0) {
				existRentPersonInfo
						.setName(this.getStartComma(rentPersonInfo.getName()));
				existRentPersonInfo.setAddress(this.getStartComma(rentPersonInfo
						.getAddress()));
				existRentPersonInfo.setPostcode(this.getStartComma(rentPersonInfo
						.getPostcode()));
				existRentPersonInfo.setOperateDate(DateUtil.currentTimestamp());
				
				existRentPersonInfo.setEmail(rentPersonInfo.getEmail());
				existRentPersonInfo.setName(rentPersonInfo.getName());
				existRentPersonInfo.setPostcode(rentPersonInfo.getPostcode());
				
				existRentPersonInfo.setEmergencyContact(rentPersonInfo.getEmergencyContact());
				existRentPersonInfo.setEmergencyContactMobile(rentPersonInfo.getEmergencyContactMobile());
				existRentPersonInfo.setRelation(rentPersonInfo.getRelation());
				
				rentPersonMapper.update(existRentPersonInfo);
				//order.setRentPersonId(rentPersonInfo.getId());
			} else {
				existRentCompanyInfo.setName(rentCompanyInfo.getName());
				existRentCompanyInfo.setAddress(rentCompanyInfo.getAddress());
				existRentCompanyInfo.setPostcode(rentCompanyInfo.getPostcode());
				//rentCompanyInfo.setCreateDate(DateUtil.currentTimestamp());
				existRentCompanyInfo.setOperateDate(DateUtil.currentTimestamp());				
				
				existRentCompanyInfo.setActualController(rentCompanyInfo.getActualController());
				existRentCompanyInfo.setControllerMobile(rentCompanyInfo.getControllerMobile());			
				
				existRentCompanyInfo.setEmergencyContact(rentCompanyInfo.getEmergencyContact());
				existRentCompanyInfo.setEmergencyContactMobile(rentCompanyInfo.getEmergencyContactMobile());
				existRentCompanyInfo.setRelation(rentCompanyInfo.getRelation());
				
				existRentCompanyInfo.setLegalName(rentCompanyInfo.getLegalName());
				existRentCompanyInfo.setLegalMobile(rentCompanyInfo.getLegalMobile());
				existRentCompanyInfo.setLegalEmail(rentCompanyInfo.getLegalEmail());
				
				rentCompanyMapper.update(existRentCompanyInfo);
				//order.setRentCompanyId(rentCompanyInfo.getId());
			}

			exist.setStartDate(DateUtil.string2Timestamp(order.getStartDatetime(),
					DateUtil.PATTERN_DATE));
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(DateUtil.string2Date(order.getStartDatetime(), DateUtil.PATTERN_DATE));					//setTimeInMillis(order.getStartDate().getTime()); 
			calendar.add(Calendar.MONTH, order.getRentDate());
			String endDate = DateUtil.date2String(calendar.getTime(),
					DateUtil.PATTERN_DATE);
			exist.setEndDate(DateUtil.string2Timestamp(endDate,
					DateUtil.PATTERN_DATE));
			//order.setCreateDate(DateUtil.currentTimestamp());
			exist.setOperateDate(DateUtil.currentTimestamp());	
			exist.setRemark(order.getRemark());
			exist.setDetailLocation(order.getDetailLocation());
			
		//System.out.println("exist.getCustomerName(): " + exist.getCustomerName());
		
		orderMapper.update(exist);
	}
}
