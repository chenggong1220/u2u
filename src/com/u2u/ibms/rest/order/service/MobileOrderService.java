package com.u2u.ibms.rest.order.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jpush.api.utils.StringUtils;

import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.AutoSubOrderService;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.beans.UserInfo;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.RentCompanyMapper;
import com.u2u.ibms.common.mapper.RentPersonMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.common.mapper.UserInfoMapper;
import com.u2u.ibms.common.util.CommonIdGenerator;
import com.u2u.ibms.rest.order.vo.OrderListResponse;
import com.u2u.ibms.rest.order.vo.OrderRequest;
import com.u2u.ibms.rest.order.vo.OrderResponse;
import com.u2u.ibms.rest.order.vo.RentSideInfo;
import com.u2u.ibms.rest.order.vo.SaveOrderRequest;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class MobileOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private SubOrderMapper subOrderMapper;
	@Autowired
	private RentPersonMapper rentPersonMapper;
	@Autowired
	private RentCompanyMapper rentCompanyMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private AssetTypeMapper assetTypeMapper;
	@Autowired
	private AutoSubOrderService autoSubOrderService;

	public void saveOrder(SaveOrderRequest request) throws Exception {
		UserInfo userinfo = userInfoMapper.getUserByUserName(request
				.getUsername());
		Order order = new Order();
		String leaseType = request.getLeaseType();
		if (!StringUtils.isEmpty(leaseType)) {
			RentSideInfo rentSideInfo = request.getRentSideInfo();
			if (Constants.PERSON_RENT_TYPE.equals(leaseType)) { // =============个人租赁
				RentPersonInfo rentPersonInfo = new RentPersonInfo();
				rentPersonInfo.setName(rentSideInfo.getName());// 姓名
				rentPersonInfo.setMobile(rentSideInfo.getMobile());// 电话
				rentPersonInfo.setEmail(rentSideInfo.getEmail());// Email
				rentPersonInfo.setIdCardFrontImg(rentSideInfo.getIdCardFrontImg());
				rentPersonInfo.setIdCardBackImg(rentSideInfo.getIdCardBackImg());
				rentPersonInfo.setIdCardHandImg(rentSideInfo.getIdCardHandImg());
				rentPersonInfo.setPersonProvinceId(rentSideInfo
						.getPersonProvinceId());
				rentPersonInfo.setPersonCityId(rentSideInfo.getPersonCityId());
				rentPersonInfo.setAddress(rentSideInfo.getAddress());// 通讯地址
				rentPersonInfo.setPostcode(rentSideInfo.getPostcode());// 邮编
				rentPersonInfo.setRelation(rentSideInfo.getRelation());// 紧急联系人关系
				rentPersonInfo.setEmergencyContact(rentSideInfo
						.getEmergencyContact());// 紧急联系人
				rentPersonInfo.setEmergencyContactMobile(rentSideInfo
						.getEmergencyContactMobile());// 紧急联系人电话.
				rentPersonInfo.setCreateDate(DateUtil.currentTimestamp());
				rentPersonInfo.setOperateDate(DateUtil.currentTimestamp());
				rentPersonMapper.insert(rentPersonInfo);
				order.setRentPersonId(rentPersonInfo.getId());
			} else { // ===================企业租赁
				RentCompanyInfo renCompanyInfo = new RentCompanyInfo();
				renCompanyInfo.setName(rentSideInfo.getName());// 企业名称
				renCompanyInfo.setPostcode(rentSideInfo.getPostcode());// 邮编
				renCompanyInfo.setBusinessLicensePath(rentSideInfo
						.getBusinessLicensePath());// 营业执照
				renCompanyInfo.setLastYearApplicationFormPath(rentSideInfo
						.getLastYearApplicationFormPath());// 纳税申请表
				renCompanyInfo.setLegalName(rentSideInfo.getLegalName());// 法人代表
				renCompanyInfo.setLegalMobile(rentSideInfo.getLegalMobile());// 法人电话
				renCompanyInfo.setLegalEmail(rentSideInfo.getLegalEmail());// 法人邮箱
				renCompanyInfo.setCertificatePath(rentSideInfo
						.getCertificatePath());// 证件
				renCompanyInfo.setEmergencyContact(rentSideInfo
						.getEmergencyContact());// 紧急联系人
				renCompanyInfo.setRelation(rentSideInfo.getRelation());// 紧急联系人关系
				renCompanyInfo.setEmergencyContactMobile(rentSideInfo
						.getEmergencyContactMobile());// 紧急联系人电话
				renCompanyInfo.setPostalProvinceId(rentSideInfo
						.getCompanyProvinceId());// 通讯地址公司
				renCompanyInfo.setPostalCityId(rentSideInfo.getCompanyCityId());// 通讯地址城市
				renCompanyInfo
						.setPostalAddress(rentSideInfo.getPostalAddress());// 通讯地址
				renCompanyInfo.setAddress(rentSideInfo.getAddress());// 公司地址
				renCompanyInfo.setCreateDate(DateUtil.currentTimestamp());
				renCompanyInfo.setOperateDate(DateUtil.currentTimestamp());
				//对企业的省、市信息
				renCompanyInfo.setCompanyProvinceId(rentSideInfo.getCompanyProvinceId());
				renCompanyInfo.setCompanyCityId(rentSideInfo.getCompanyCityId());
				renCompanyInfo.setIdCardFrontImg(rentSideInfo.getIdCardFrontImg());
				renCompanyInfo.setIdCardBackImg(rentSideInfo.getIdCardBackImg());
				renCompanyInfo.setIdCardHandImg(rentSideInfo.getIdCardHandImg());
				rentCompanyMapper.insert(renCompanyInfo);
				order.setRentCompanyId(renCompanyInfo.getId());

			}
			order.setRentType(request.getRentType());// 租赁方式.
			order.setCode(CommonIdGenerator.generateOrderId());
			order.setStartDate(string2Timestamp(request.getStartTime()
					+ " 00:00:00"));// 租赁开始时间
			order.setEndDate(string2Timestamp(request.getEndTime()
					+ " 00:00:00"));// 租赁结束时间
			order.setRentDate(getMonthSpace(request.getStartTime(),
					request.getEndTime()));
			order.setProvinceId(request.getProvince());// 租赁物所在省
			order.setCityId(request.getCity());// 租赁物所在市
			order.setDetailLocation(request.getDetailLocation());
			order.setRentPersonType(Integer.parseInt(leaseType));
			order.setStatus(Constants.ORDER_1_APPLY);// 申请
			order.setUserId(userinfo.getId());
			order.setDeposit(Float.parseFloat(request.getAllMoney()));
			order.setNeedInvoices("1".equals(request.getNeedInvoices()) ? true
					: false);
			order.setCreateDate(DateUtil.currentTimestamp());
			order.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.insert(order);
			List<SubOrder> subOrders = request.getSubOrders();
			for (SubOrder subOrder : subOrders) {
				subOrder.setOrderId(order.getId());
				AssetType assetType = assetTypeMapper.getById(subOrder
						.getAssetTypeId());
				subOrder.setDeposit(assetType.getDeposit());
				subOrder.setAmount(assetType.getAmount());
				subOrder.setCreateDate(DateUtil.currentTimestamp());
				subOrder.setOperateDate(DateUtil.currentTimestamp());
				subOrderMapper.insert(subOrder);
			}
		}

		autoSubOrderService.seperate(order);
	}

	private String timestamp2String(Timestamp tempstame) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp now = new Timestamp(tempstame.getTime());// 获取系统当前时间
		String str = df.format(now);
		return str;
	}

	private Timestamp string2Timestamp(String str) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time;
		Timestamp ts = null;
		try {
			time = df.format(df.parse(str));
			ts = Timestamp.valueOf(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(getMonthSpace("2016-08-07", "2016-12-15"));
		System.out.println(getMonthSpace("2016-11-07", "2016-12-15"));
		System.out.println(getMonthSpace("2016-12-07", "2017-06-15"));
		System.out.println(getMonthSpace("2016-12-07", "2018-12-15"));
	}

	public static int getMonthSpace(String date1, String date2)
			throws ParseException {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));
		int c1Year = c1.get(Calendar.YEAR);
		int c1Month = c1.get(Calendar.MONTH);
		// int c1Day = c1.get(Calendar.DAY_OF_MONTH);
		int c2Year = c2.get(Calendar.YEAR);
		int c2Month = c2.get(Calendar.MONTH);
		// int c2Day = c2.get(Calendar.DAY_OF_MONTH);

		if (c1Month > c2Month) {
			result = 12 - c1Month + c2Month + (c2Year - c1Year - 1) * 12;
		} else {
			result = c2Month - c1Month + (c2Year - c1Year) * 12;
		}
		return result == 0 ? 1 : Math.abs(result);

	}

	public List<OrderListResponse> getOrderList(String username) {
		UserInfo userinfo = userInfoMapper.getUserByUserName(username);
		List<Order> orders = orderMapper.getByUser(userinfo);
		List<OrderListResponse> list = new ArrayList<OrderListResponse>();
		for (Order order : orders) {
			OrderListResponse response = null;
			List<SubOrder> subOrders = subOrderMapper.getByOrderId(
					new RowBounds(), order.getId());
			for (SubOrder subOrder : subOrders) {
				response = new OrderListResponse();
				AssetType assetType = assetTypeMapper.getById(subOrder
						.getAssetTypeId());
				response.setAssetInfo(assetType.getModel());// 设备信息
				response.setLeaseType(order.getRentType() + "");// 租赁类型
				response.setOrderCreateTime(timestamp2String(subOrder
						.getCreateDate()));
				response.setOrderNo(order.getCode());// 订单编号
				response.setOrderId(order.getId() + "");// 主键编号
				response.setOrderStatus(order.getStatus());// 订单状态
				list.add(response);
			}
		}
		return list;
	}

	public OrderResponse getOrderById(OrderRequest request) {
		Order order = orderMapper.getById(request.getOrderId());
		List<SubOrder> subOrders = subOrderMapper.getByOrderId(new RowBounds(),
				order.getId());
		OrderResponse response = null;
		for (SubOrder subOrder : subOrders) {
			response = new OrderResponse();
			AssetType assetType = assetTypeMapper.getById(subOrder
					.getAssetTypeId());
			response.setAssetInfo(assetType.getModel());// 设备信息
			response.setLeaseType(order.getRentType() + "");// 租赁类型
			response.setOrderCreateTime(order.getCreateDate() + "");
			response.setOrderNo(order.getCode() + "");// 订单编码
			response.setOrderId(order.getId() + "");// 主键编号
			response.setOrderStatus(order.getStatus());// 订单状态
			response.setBond(order.getDeposit() + "");// 保证金
		}
		return response;
	}
}
