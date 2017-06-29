package com.u2u.ibms.web.project.service;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.u2u.common.component.dingding.DingDingAuthUtil;
import com.u2u.framework.base.BaseService;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.mapper.AuthorizeMapper;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.util.DateUtil;
import com.u2u.ibms.common.Constants;
import com.u2u.ibms.common.beans.AssetType;
import com.u2u.ibms.common.beans.Contract;
import com.u2u.ibms.common.beans.CreditVerify;
import com.u2u.ibms.common.beans.MailHistory;
import com.u2u.ibms.common.beans.Order;
import com.u2u.ibms.common.beans.Project;
import com.u2u.ibms.common.beans.ProjectHandle;
import com.u2u.ibms.common.beans.ProjectPicture;
import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.beans.SubOrder;
import com.u2u.ibms.common.mapper.AssetTypeMapper;
import com.u2u.ibms.common.mapper.ComboMapper;
import com.u2u.ibms.common.mapper.ContractMapper;
import com.u2u.ibms.common.mapper.CreditVerifyMapper;
import com.u2u.ibms.common.mapper.MailHistoryMapper;
import com.u2u.ibms.common.mapper.OrderMapper;
import com.u2u.ibms.common.mapper.ProjectHandleMapper;
import com.u2u.ibms.common.mapper.ProjectMapper;
import com.u2u.ibms.common.mapper.ProjectPictureMapper;
import com.u2u.ibms.common.mapper.RentCompanyMapper;
import com.u2u.ibms.common.mapper.RentPersonMapper;
import com.u2u.ibms.common.mapper.SubOrderMapper;
import com.u2u.ibms.common.util.CommonIdGenerator;
import com.u2u.ibms.common.util.contract.NaturePersonGuaranteeGenerator;
import com.u2u.ibms.common.util.contract.UserContract;
import com.u2u.ibms.common.util.contract.vo.ContractConstructor;
import com.u2u.ibms.common.util.contract.vo.ContractFileVo;
import com.u2u.ibms.common.util.contract.vo.ContractRentAsset;
import com.u2u.ibms.common.util.contract.vo.ContractYifang;
import com.u2u.ibms.web.order.service.OrderService;
import com.u2u.ibms.web.project.condition.ProjectCondition;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ProjectService extends BaseService {

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private ProjectHandleMapper projectHandleMapper;

	@Autowired
	private CreditVerifyMapper creditVerifyMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private ProjectPictureMapper projectPictureMapper;

	@Autowired
	private SubOrderMapper subOrderMapper;

	@Autowired
	private AssetTypeMapper assetTypeMapper;

	@Autowired
	private ComboMapper comboMapper;
	@Autowired
	private RentPersonMapper rentPersonMapper;
	@Autowired
	private RentCompanyMapper rentCompanyMapper;

	@Autowired
	private MailHistoryMapper mailHistoryMapper;

	@Autowired
	private AuthorizeMapper authorizeMapper;

	public List<Project> getAll(RowBounds rb, ProjectCondition condition) {
		List<Project> projects = projectMapper.getAll(rb,
				getStringCondition(condition.getProjectId()),
				getIntegerCondition(condition.getResult()),
				getIntegerCondition(condition.getCreditResult()),
				getStringCondition(condition.getOperatorId()),
				getStartDate(condition), getEndDate(condition),
				getStringCondition(condition.getOrderCode()),
				getStringCondition(condition.getOperator()));
		for (final Project project : projects) {
			this.convertToProject(project);
		}
		return projects;
	}

	public List<ProjectHandle> getProjectHandleByProjectId(int projectId) {
		//Change getAllByProjId from getAllByProjectId for getting real agent name, SUNZHE, 2017-06-19
		return projectHandleMapper.getAllByProjId(projectId);	
	}

	public ProjectHandle getProjectHandlerByProjectIdAndLevel(int projectId,
			int level) {
		return projectHandleMapper.getAllByProjectIdAndLevel(projectId, level);
	}

	public Project getById(int id) {
		Project project = projectMapper.getById(id);
		return convertToProject(project);
	}

	private Project convertToProject(final Project project) {
		Order order = orderService.getById(project.getOrderId());
		if (order.getRentPersonType() == 0) {
			RentPersonInfo person = rentPersonMapper.getById(order
					.getRentPersonId());
			order.setCustomerName(person.getName());
		} else {
			RentCompanyInfo company = rentCompanyMapper.getById(order
					.getRentCompanyId());
			order.setCustomerName(company.getName());
		}
		project.setOrder(order);
		return project;
	}

	public void insert(Project project) {
		project.setCreateDate(DateUtil.currentTimestamp());
		project.setOperateDate(DateUtil.currentTimestamp());
		projectMapper.insert(project);
	}

	public void save(Project project, Order order, HttpServletRequest request) {
		Project exist = projectMapper.getById(project.getId());

		exist.setRequired1(project.isRequired1());
		exist.setRequired1Content(project.getRequired1Content());
		exist.setRequired2(project.isRequired2());
		exist.setRequired2Content(project.getRequired2Content());
		exist.setRequired3(project.isRequired3());
		exist.setRequired3Content(project.getRequired3Content());
		exist.setRequired4(project.isRequired4());
		exist.setRequired4Content(project.getRequired4Content());
		exist.setRequired5(project.isRequired5());
		exist.setRequired5Content(project.getRequired5Content());
		exist.setRequired6(project.isRequired6());
		exist.setRequired6Content(project.getRequired6Content());

		exist.setResult(1);
		exist.setIdentifyImage(project.getIdentifyImage());
		exist.setCreditResult(0);
		exist.setFeedback(project.getFeedback());
		exist.setOperateDate(DateUtil.currentTimestamp());
		projectMapper.update(exist);

		ProjectHandle projectHandle = projectHandleMapper
				.getAllByProjectIdAndLevel(project.getId(), 1);
		if (projectHandle == null) {
			projectHandle = new ProjectHandle();
			projectHandle.setProjectId(project.getId());
			projectHandle.setLevel(1);
			projectHandle.setUsername(SecurityContextUtil.getUserName());
			projectHandle.setStatus(true);
			projectHandle.setFeedback("");
			projectHandle.setCreateDate(DateUtil.currentTimestamp());
			projectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.insert(projectHandle);
		} else {
			projectHandle.setProjectId(project.getId());
			projectHandle.setUsername(SecurityContextUtil.getUserName());
			projectHandle.setStatus(true);
			projectHandle.setFeedback("");
			projectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.update(projectHandle);
		}

		Order existOrder = orderMapper.getById(exist.getOrderId());
		existOrder.setOperator(order.getOperator());
		existOrder.setOperatorMobile(order.getOperatorMobile());
		existOrder.setStatus(Constants.ORDER_3_PROJECT_HANDLE);
		existOrder.setOperateDate(DateUtil.currentTimestamp());
		orderMapper.update(existOrder);
		
//Start: Added Company Code, by SUNZHE, 2017-01-16
		
		if (existOrder.getRentPersonType() == 0) {
			//RentPersonInfo person = rentPersonMapper.getById(existOrder.getRentPersonId());
		} else {
			//RentCompanyInfo rentCompanyInfo = existOrder.getRentCompanyInfo();
			//rentCompanyInfo.setCompCode(request.getParameter("compCode"));
			int compID = existOrder.getRentCompanyId();
			RentCompanyInfo rentCompanyInfo = rentCompanyMapper.getById(compID);	
			rentCompanyInfo.setCompCode(request.getParameter("compCode"));
			rentCompanyMapper.update(rentCompanyInfo);
		}	
		
//End: Added Company Code, by SUNZHE, 2017-01-16	
		
		

		Enumeration<String> enums = request.getParameterNames();
		projectPictureMapper.delete(exist.getId());
		while (enums.hasMoreElements()) {
			String item = enums.nextElement();
			if (item.startsWith("identifyImage_")) {
				Integer projectIndex = Integer.valueOf(item.split("_")[1]);
				ProjectPicture projectPicture = projectPictureMapper
						.getByProjectIdAndIndex(exist.getId(), projectIndex);

				if (projectPicture == null) {
					projectPicture = new ProjectPicture();
					projectPicture.setProjectId(exist.getId());
					projectPicture.setPictureIndex(projectIndex);
					projectPicture.setPicture(request.getParameter(item));
					projectPicture.setCreateDate(DateUtil.currentTimestamp());
					projectPicture.setOperateDate(DateUtil.currentTimestamp());
					projectPictureMapper.insert(projectPicture);
				} else {
					projectPicture.setPicture(request.getParameter(item));
					projectPicture.setOperateDate(DateUtil.currentTimestamp());
					projectPictureMapper.update(projectPicture);
				}

			}
		}

		DingDingAuthUtil.send(Constants.DINGDING_4_PROJECT_CHECK);
	}

	public void multicheckSave(Project project, ProjectHandle projectHandle) {

		Project exist = projectMapper.getById(project.getId());
		exist.setResult(2);
		exist.setOperateDate(DateUtil.currentTimestamp());
		projectMapper.update(exist);

		ProjectHandle existProjectHandle = projectHandleMapper
				.getAllByProjectIdAndLevel(project.getId(), 2);
		if (existProjectHandle == null) {
			projectHandle.setProjectId(project.getId());
			projectHandle.setLevel(2);
			projectHandle.setUsername(SecurityContextUtil.getUserName());
			projectHandle.setStatus(projectHandle.isStatus());
			projectHandle.setFeedback(projectHandle.getFeedback());
			projectHandle.setCreateDate(DateUtil.currentTimestamp());
			projectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.insert(projectHandle);
		} else {
			existProjectHandle.setUsername(SecurityContextUtil.getUserName());
			existProjectHandle.setStatus(projectHandle.isStatus());
			existProjectHandle.setFeedback(projectHandle.getFeedback());
			existProjectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.update(existProjectHandle);
		}

		if (projectHandle.isStatus()) {
			Order existOrder = orderMapper.getById(exist.getOrderId());
			existOrder.setStatus(Constants.ORDER_4_PROJECT_CHECK);
			existOrder.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(existOrder);

			DingDingAuthUtil.send(Constants.DINGDING_5_CREDIT_CHECK);
		} else {
			Order existOrder = orderMapper.getById(exist.getOrderId());
			existOrder.setStatus(Constants.ORDER_000_ABANDONED);
			existOrder.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(existOrder);

			exist.setResult(3);
			projectMapper.update(exist);
		}
	}

	public void creditVerify(Project project, ProjectHandle projectHandle,
			CreditVerify creditVerify,String creditFile) {
		Project exist = projectMapper.getById(project.getId());
		exist.setCreditResult(1);
		exist.setOperateDate(DateUtil.currentTimestamp());
		projectMapper.update(exist);

		ProjectHandle existProjectHandle = projectHandleMapper
				.getAllByProjectIdAndLevel(project.getId(), 3);
		if (existProjectHandle == null) {
			projectHandle.setProjectId(project.getId());
			projectHandle.setLevel(3);
			projectHandle.setUsername(SecurityContextUtil.getUserName());
			projectHandle.setStatus(projectHandle.isStatus());
			projectHandle.setFeedback(projectHandle.getFeedback());
			projectHandle.setCreateDate(DateUtil.currentTimestamp());
			projectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.insert(projectHandle);
		} else {
			existProjectHandle.setUsername(SecurityContextUtil.getUserName());
			existProjectHandle.setStatus(projectHandle.isStatus());
			existProjectHandle.setFeedback(projectHandle.getFeedback());
			existProjectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.update(existProjectHandle);
		}

		CreditVerify existCredit = creditVerifyMapper.getByProjectId(project
				.getId());
		if (existCredit == null) {
			creditVerify.setProjectId(project.getId());
			creditVerify.setCreateDate(DateUtil.currentTimestamp());
			creditVerify.setOperateDate(DateUtil.currentTimestamp());
			creditVerifyMapper.insert(creditVerify);
		} else {
			existCredit.setCreditRequired1(creditVerify.getCreditRequired1());
			existCredit.setCreditRequired2(creditVerify.getCreditRequired2());
			existCredit.setCreditRequired3(creditVerify.getCreditRequired3());
			existCredit.setRequired1Content(creditVerify.getRequired1Content());
			existCredit.setRequired2Content(creditVerify.getRequired2Content());
			existCredit.setRequired3Content(creditVerify.getRequired3Content());
			existCredit.setRequired4Content(creditVerify.getRequired4Content());
			existCredit.setRequired6Content(creditVerify.getRequired6Content());
			existCredit.setOperateDate(DateUtil.currentTimestamp());
			creditVerifyMapper.update(existCredit);
		}

		Order existOrder = orderService.getById(exist.getOrderId());
		existOrder.setCreditFile(creditFile);
		if (projectHandle.isStatus()) {
			existOrder.setStatus(Constants.ORDER_5_CREDIT_CHECK);
			existOrder.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(existOrder);

			DingDingAuthUtil.send(Constants.DINGDING_6_CREDIT_MULTI_CHECK);
		} else {

			existOrder.setStatus(Constants.ORDER_001_CREDIT_ABANDONED);
			existOrder.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(existOrder);

			exist.setResult(4);
			exist.setCreditResult(3);
			projectMapper.update(exist);

			String customerName = null;
			if (existOrder.getRentPersonType() == 0) {
				customerName = existOrder.getRentPersonInfo().getName();
			} else {
				customerName = existOrder.getRentCompanyInfo().getLegalName();
			}

			User manager = authorizeMapper.getUserById(existOrder
					.getOperatorId());
			DingDingAuthUtil.send(manager, MessageFormat.format(
					"客户{0}的租赁申请项目已被信审拒绝，项目编号{1}，信审处理意见：{2}", customerName,
					exist.getProjectId(), projectHandle.getFeedback()));

		}
	}

	public void creditVerifyMulticheck(Project project,
			ProjectHandle projectHandle, HttpServletRequest request)
			throws Exception {

		Project exist = projectMapper.getById(project.getId());
		exist.setCreditResult(2);
		exist.setOperateDate(DateUtil.currentTimestamp());
		projectMapper.update(exist);

		ProjectHandle existProjectHandle = projectHandleMapper
				.getAllByProjectIdAndLevel(project.getId(), 4);
		if (existProjectHandle == null) {
			projectHandle.setProjectId(project.getId());
			projectHandle.setLevel(4);
			projectHandle.setUsername(SecurityContextUtil.getUserName());
			projectHandle.setStatus(projectHandle.isStatus());
			projectHandle.setFeedback(projectHandle.getFeedback());
			projectHandle.setCreateDate(DateUtil.currentTimestamp());
			projectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.insert(projectHandle);
		} else {
			existProjectHandle.setUsername(SecurityContextUtil.getUserName());
			existProjectHandle.setStatus(projectHandle.isStatus());
			existProjectHandle.setFeedback(projectHandle.getFeedback());
			existProjectHandle.setOperateDate(DateUtil.currentTimestamp());
			projectHandleMapper.update(existProjectHandle);
		}

		Order existOrder = orderService.getById(exist.getOrderId());

		String customerName = null;
		if (existOrder.getRentPersonType() == 0) {
			customerName = existOrder.getRentPersonInfo().getName();
		} else {
			customerName = existOrder.getRentCompanyInfo().getLegalName();
		}

		String dingdingMessage = null;
		
		if (projectHandle.isStatus()) {
			existOrder.setStatus(Constants.ORDER_6_CREDIT_MULTI_CHECK);
			existOrder.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(existOrder);

			Contract contract = contractMapper.getByOrderId(exist.getOrderId());
			if (contract == null) {
				contract = new Contract();
				contract.setOrderId(exist.getOrderId());
				contract.setProjectId(exist.getId());
				contract.setContractId(CommonIdGenerator.generateContractId());
				contract.setSendStatus(false);
				contract.setBackStatus(false);
				contract.setCheckin(false);
				contract.setCheckinDate(null);
				contract.setSignoff(false);
				contract.setSignoffDate(null);
				ContractFile contractFile = this.getContractFile(request,
						contract.getContractId());
				contract.setSaveLocation(contractFile.getLocation());
				contract.setStatus("待审");
				contract.setCreateDate(DateUtil.currentTimestamp());
				contract.setOperateDate(DateUtil.currentTimestamp());
				contractMapper.insert(contract);

				Order order = orderService.getById(existOrder.getId());
				order.setSubOrders(subOrderMapper.getByOrderId(new RowBounds(),
						order.getId()));
				Map<String, String> map = new HashMap<String, String>();
				Date current = new Date();
				map.put("title", "租赁合同-" + contract.getContractId());
				map.put("year", new SimpleDateFormat("yyyy").format(current));
				map.put("month", new SimpleDateFormat("MM").format(current));
				map.put("day", new SimpleDateFormat("dd").format(current));

				Map<String, File> contractFiles = new HashMap<String, File>();
				contractFiles.put(
						MimeUtility.encodeText(contract.getContractId()
								+ "_服务商义务确认书"),
						new File(request.getSession().getServletContext()
								.getRealPath("/styles/vendorcheck.pdf")));
				contractFiles.put(
						MimeUtility.encodeText(contract.getContractId()
								+ "_优尼斯租赁业务提示函"),
						new File(request.getSession().getServletContext()
								.getRealPath("/styles/tsh.pdf")));
				contractFiles.put(
						MimeUtility.encodeText(contract.getContractId()
								+ "_租赁物数据信息采集使用政策"),
						new File(request.getSession().getServletContext()
								.getRealPath("/styles/zc.pdf")));

				MailHistory mailHistory = new MailHistory();

				if (order.getRentPersonType() == 0) {
					new UserContract(this.getUserContractFileVo(request, order,
							contract)).generateFile(contractFile.getFile());
					contractFiles.put(contract.getContractId(),
							contractFile.getFile());
					RentPersonInfo personInfo = order.getRentPersonInfo();
					// MessageManager.sendMailAndFile(personInfo.getEmail(),
					// map,
					// MailTemplateKey.contractVm, contractFiles);
					mailHistory.setSendTo(personInfo.getEmail());
				} else {
					ContractFileVo contractFileVo = this.getShopContractFileVo(
							request, order, contract);
					new UserContract(contractFileVo).generateFile(contractFile
							.getFile());
					RentCompanyInfo companyInfo = order.getRentCompanyInfo();
					ContractFile natualPersonGuarantee = this.getContractFile(
							request, contract.getContractId()
									+ "_nature_guarantee");
					new NaturePersonGuaranteeGenerator(contractFileVo)
							.generateFile(natualPersonGuarantee.getFile());
					contractFiles.put(
							MimeUtility.encodeText(contract.getContractId()
									+ "_自然人保证函"),
							natualPersonGuarantee.getFile());
					contractFiles.put(contract.getContractId(),
							contractFile.getFile());

					// MessageManager.sendMailAndFile(companyInfo.getLegalEmail(),
					// map, MailTemplateKey.contractVm, contractFiles);

					mailHistory.setSendTo(companyInfo.getLegalEmail());
				}

				mailHistory.setOperatorId(order.getOperatorId());
				mailHistory.setContractId(contract.getId());
				mailHistory.setTitle(map.get("title"));
				mailHistory.setContractFile(contract.getSaveLocation());

				for (Entry<String, File> contractFileItem : contractFiles
						.entrySet()) {
					mailHistory.setAttach((mailHistory.getAttach() == null ? ""
							: (mailHistory.getAttach() + ";"))
							+ contractFileItem.getKey());
				}

				mailHistory.setCreateDate(DateUtil.currentTimestamp());
				mailHistory.setOperateDate(DateUtil.currentTimestamp());
				// mailHistoryMapper.insert(mailHistory);
			} else {
				contract.setOperateDate(DateUtil.currentTimestamp());
				contractMapper.update(contract);
			}

			// DingDingAuthUtil.send(Constants.DINGDING_7_CONTRACT_RECEIVE);
			dingdingMessage = MessageFormat.format("客户{0}的租赁合同已经生成，请及时发送给客户确认",
					customerName);
		} else {
			existOrder.setStatus(Constants.ORDER_001_CREDIT_ABANDONED);
			existOrder.setOperateDate(DateUtil.currentTimestamp());
			orderMapper.update(existOrder);

			exist.setResult(4);
			exist.setCreditResult(3);
			projectMapper.update(exist);
			dingdingMessage = MessageFormat.format(
					"客户{0}的租赁申请项目已被信审拒绝，项目编号{1}，信审处理意见：{2}", customerName,
					exist.getProjectId(), projectHandle.getFeedback());
		}

		User manager = authorizeMapper.getUserById(existOrder.getOperatorId());
		DingDingAuthUtil.send(manager, dingdingMessage);
	}

	private ContractFile getContractFile(HttpServletRequest request, String file) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		/** 构建图片保存的目录 **/
		String logoPathDir = "/styles/contract/"
				+ dateformat.format(new Date());
		/** 得到图片保存目录的真实路径 **/
		String logoRealPathDir = request.getSession().getServletContext()
				.getRealPath(logoPathDir);
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();
		/** 拼成完整的文件保存路径加文件 **/
		String fileName = logoRealPathDir + "/" + file + ".pdf";
		ContractFile contractFile = new ContractFile();
		contractFile.setFile(new File(fileName));
		contractFile.setLocation(logoPathDir + "/" + file + ".pdf");
		return contractFile;
	}

	private ContractFileVo getUserContractFileVo(HttpServletRequest request,
			Order order, Contract contractInfo) {
		final ContractFileVo contract = new ContractFileVo();

		this.getContractFileVo(contract, request, order, contractInfo);
		RentPersonInfo person = order.getRentPersonInfo();
		ContractYifang contractUserYifang = new ContractYifang();
		contractUserYifang.setName(person.getName());
		contractUserYifang.setAddress(person.getPersonProvince()
				+ person.getPersonCity() + person.getAddress());
		//如果是个人客户，法人代表为“/”Modified by SUNZHE, 2017-01-18
		contractUserYifang.setLegalPerson("/");
		
		contractUserYifang.setPostcode(person.getPostcode());
		contractUserYifang.setMobile(person.getMobile());
		contractUserYifang.setEmail(person.getEmail());
		contractUserYifang.setIdentifier(order.getIdCard());
		contractUserYifang.setEmergency(person.getEmergencyContact());
		contractUserYifang.setEmergencyRelation(person.getRelation());
		contractUserYifang.setEmergencyMobile(person
				.getEmergencyContactMobile());
		contract.setContractYifang(contractUserYifang);
		return contract;
	}

	private ContractFileVo getShopContractFileVo(HttpServletRequest request,
			Order order, Contract contractInfo) {
		final ContractFileVo contract = new ContractFileVo();
		this.getContractFileVo(contract, request, order, contractInfo);
		RentCompanyInfo company = order.getRentCompanyInfo();
		ContractYifang contractShopYifang = new ContractYifang();
		contractShopYifang.setName(company.getName());
		contractShopYifang.setAddress(company.getCompanyProvince()
				+ company.getCompanyCity() + company.getPostalAddress());
		contractShopYifang.setLegalPerson(company.getLegalName());
		contractShopYifang.setPostcode(company.getPostcode());
		contractShopYifang.setMobile(company.getLegalMobile());
		contractShopYifang.setEmail(company.getLegalEmail());
		//Start: Changed ID No for Comp, by SUNZHE, 2017-01-16
		//contractShopYifang.setIdentifier(order.getIdCard());	
		contractShopYifang.setIdentifier(company.getCompCode());	
		//End: Changed ID No for Comp, by SUNZHE, 2017-01-16
		contractShopYifang.setEmergency(company.getEmergencyContact());
		contractShopYifang.setEmergencyRelation(company.getRelation());
		contractShopYifang.setEmergencyMobile(company
				.getEmergencyContactMobile());

		contract.setContractYifang(contractShopYifang);
		return contract;
	}

	private void getContractFileVo(final ContractFileVo contractFileVo,
			HttpServletRequest request, Order order, Contract contractInfo) {

		contractFileVo.setTransforAddress(order.getProvince() + order.getCity()
				+ order.getDetailLocation());
		contractFileVo.setUseAddress(contractFileVo.getTransforAddress());

		contractFileVo.setContractId(contractInfo.getContractId());
		contractFileVo.setLogo(new File(request.getSession()
				.getServletContext().getRealPath("/styles/logo.png")));
		contractFileVo.setZiti(new File(request.getSession()
				.getServletContext().getRealPath("/styles/arialuni.ttf")));
		contractFileVo.setSealFile(new File(request.getSession()
				.getServletContext().getRealPath("/styles/yinzhang.png")));
		contractFileVo.setSignatureFile(new File(request.getSession()
				.getServletContext().getRealPath("/styles/gmsignature.png")));	//Added for GMSignature, by SUNZHE, 2017-01-11
		
		//经办人由业务专员修改为客户专员,by SUNZHE, 2017-01-18
		contractFileVo.setOperator(order.getManager().getRealname());
		contractFileVo.setOperatorMobile(order.getManager().getMobile());
		//Add Rent Type by SUNHE, 2017-01-18
		contractFileVo.setRentType(order.getRentType());

		ContractConstructor constructor = new ContractConstructor();
		float count = 0;

		if (CollectionUtils.isNotEmpty(order.getSubOrders())) {
			constructor.setDeposit(order.getSubOrders().get(0).getDeposit()
					+ "");
			List<ContractRentAsset> assets = new ArrayList<ContractRentAsset>();
			for (SubOrder subOrder : order.getSubOrders()) {
				ContractRentAsset asset = new ContractRentAsset();
				AssetType assetType = assetTypeMapper.getById(subOrder
						.getAssetTypeId());
				asset.setBrand(assetType.getBrand());
				asset.setModel(assetType.getModel());

				asset.setAmount(comboMapper.getById(subOrder.getComboId())
						.getAmount() + "");
				asset.setCount(subOrder.getCount() + "");
				// modify be dean
				count = count + subOrder.getCount()
						* assetType.getInsuranceAmount();
				assets.add(asset);
			}
			contractFileVo.setAssets(assets);
		} else {
			constructor.setDeposit("0.00");
			contractFileVo.setAssets(new ArrayList<ContractRentAsset>());
		}

		constructor.setTotalDate(order.getRentDate() + "");
		contractFileVo.setContractConstructor(constructor);
		contractFileVo.setDeposit(order.getDeposit() + "");
		// modify be dean
		contractFileVo.setInsuranceServeAmount((order.getRentDate() * count)
				+ "");
		contractFileVo.setAmountTotal((Float.valueOf(contractFileVo
				.getDeposit()) + Float.valueOf(contractFileVo
				.getInsuranceServeAmount()))
				+ "");
	}

	public CreditVerify getCreditVerifyByProjectId(int projectId) {
		CreditVerify credit = creditVerifyMapper.getByProjectId(projectId);
		if (credit != null) {
			credit.setCreditRequired1Name(getStatus(credit.getCreditRequired1()));
			credit.setCreditRequired2Name(getStatus(credit.getCreditRequired2()));
			credit.setCreditRequired3Name(getStatus(credit.getCreditRequired3()));
		}
		return credit;
	}

	private String getStatus(int status) {
		if (status == 1) {
			return "无异常";
		} else if (status == 2) {
			return "异常";
		} else if (status == 3) {
			return "通过";
		} else if (status == 4) {
			return "不通过";
		}
		return "无异常";
	}

	public void delete(int id) {
		projectMapper.delete(id);
	}
}

class ContractFile {
	private File file;
	private String location;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}