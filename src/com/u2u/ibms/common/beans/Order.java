package com.u2u.ibms.common.beans;

import java.sql.Timestamp;
import java.util.List;

import com.u2u.framework.base.BaseBean;
import com.u2u.framework.sys.authorize.beans.User;

public class Order extends BaseBean {

	private int id;
	private String code; // 编码
	private int userId;
	private int rentType;// 租赁类型
	private int subOrderId;// 子单ID
	private Timestamp startDate;// 起始时间
	private Timestamp endDate;// 结束时间
	private String startDatetime;
	private String endDatetime;
	private int rentDate;// 租期/月
	private int provinceId;// 租赁物存放省
	private int cityId;// 租赁物存放市
	private String province;
	private String city;
	private String detailLocation;
	private String idCard;

	private float deposit;// 保证金总额
	private float leftDeposit;// 保证金总额

	private boolean depositCheck;

	private int rentPersonType;// 租赁人类型
	private int rentPersonId;// 租赁人信息
	private int rentCompanyId;// 租赁公司信息
	private boolean needInvoices;// 是否需要发票
	private int createSource;// 创建来源
	private int managerId;
	private String remark;
	private boolean assigned;// 是否分单

	private int operatorId;
	private String operator;
	private String operatorMobile;

	// 客户经理
	private String customerManager;

	private boolean delStatus = false;
	private String creditFile;
	private String feedback;// 处理意见
	private boolean feedStatus;
	private String status;// 状态【申请，基本信息审核，项目复核，合同确认，资信审核，付款，发货，合同生效，订单结束】

	private List<SubOrder> subOrders;
	private RentPersonInfo rentPersonInfo;
	private RentCompanyInfo rentCompanyInfo;
	private User manager;

	private String assetFullAdress;
	private String customerName;

	public String getCreditFile() {
		return creditFile;
	}

	public void setCreditFile(String creditFile) {
		this.creditFile = creditFile;
	}

	public boolean isDelStatus() {
		return delStatus;
	}

	public void setDelStatus(boolean delStatus) {
		this.delStatus = delStatus;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public boolean isDepositCheck() {
		return depositCheck;
	}

	public void setDepositCheck(boolean depositCheck) {
		this.depositCheck = depositCheck;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public float getLeftDeposit() {
		return leftDeposit;
	}

	public void setLeftDeposit(float leftDeposit) {
		this.leftDeposit = leftDeposit;
	}

	public int getRentDate() {
		return rentDate;
	}

	public void setRentDate(int rentDate) {
		this.rentDate = rentDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isFeedStatus() {
		return feedStatus;
	}

	public void setFeedStatus(boolean feedStatus) {
		this.feedStatus = feedStatus;
	}

	public String getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}

	public String getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<SubOrder> getSubOrders() {
		return subOrders;
	}

	public void setSubOrders(List<SubOrder> subOrders) {
		this.subOrders = subOrders;
	}

	public RentPersonInfo getRentPersonInfo() {
		return rentPersonInfo;
	}

	public void setRentPersonInfo(RentPersonInfo rentPersonInfo) {
		this.rentPersonInfo = rentPersonInfo;
	}

	public RentCompanyInfo getRentCompanyInfo() {
		return rentCompanyInfo;
	}

	public void setRentCompanyInfo(RentCompanyInfo rentCompanyInfo) {
		this.rentCompanyInfo = rentCompanyInfo;
	}

	public String getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorMobile() {
		return operatorMobile;
	}

	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}

	public String getDetailLocation() {
		return detailLocation;
	}

	public void setDetailLocation(String detailLocation) {
		this.detailLocation = detailLocation;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRentType() {
		return rentType;
	}

	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	public int getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(int subOrderId) {
		this.subOrderId = subOrderId;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public int getRentPersonType() {
		return rentPersonType;
	}

	public void setRentPersonType(int rentPersonType) {
		this.rentPersonType = rentPersonType;
	}

	public int getRentPersonId() {
		return rentPersonId;
	}

	public void setRentPersonId(int rentPersonId) {
		this.rentPersonId = rentPersonId;
	}

	public int getRentCompanyId() {
		return rentCompanyId;
	}

	public void setRentCompanyId(int rentCompanyId) {
		this.rentCompanyId = rentCompanyId;
	}

	public boolean isNeedInvoices() {
		return needInvoices;
	}

	public void setNeedInvoices(boolean needInvoices) {
		this.needInvoices = needInvoices;
	}

	public int getCreateSource() {
		return createSource;
	}

	public void setCreateSource(int createSource) {
		this.createSource = createSource;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssetFullAdress() {
		return assetFullAdress;
	}

	public void setAssetFullAdress(String assetFullAdress) {
		this.assetFullAdress = assetFullAdress;
	}

	public String getCustomerName() {
		return customerName;
	}
	

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	

}
