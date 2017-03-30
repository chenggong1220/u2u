package com.u2u.ibms.rest.order.vo;

import java.util.List;

import com.u2u.ibms.common.beans.RentCompanyInfo;
import com.u2u.ibms.common.beans.RentPersonInfo;
import com.u2u.ibms.common.beans.SubOrder;

public class SaveOrderRequest {

	// 租赁类型
	private int rentType;
	// 起始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 租赁物存放省
	private int province;
	// 租赁物存放市
	private int city;
	// 租赁物存放详细地址
	private String detailLocation;
	// 保证金总额
	private String allMoney;
	// 租赁方类型
	private String leaseType;
	// 租赁人信息
	private RentPersonInfo rentPersonInfo;
	// 租赁公司信息
	private RentCompanyInfo rentCompanyInfo;
	// 子订单
	private List<SubOrder> subOrders;
	// 订单状态
	private String orderStatus;

	private String username;
	// 需要发票
	private String needInvoices;
	private RentSideInfo rentSideInfo;
	// 证件正面
	private String idCardFrontImg;
	// 证件反面
	private String idCardBackImg;
	// 证件手持
	private String idCardHandImg;

	public String rentDuraion;		//SUNZHE, 2017-03-26
	public String totalDeposit;		//SUNZHE, 2017-03-26
	public String totalInsurance;	//SUNZHE, 2017-03-26
	
	/**
	 * 返回 租赁物存放详细地址
	 * 
	 * @return 租赁物存放详细地址
	 */
	public String getDetailLocation() {
		return detailLocation;
	}

	/**
	 * 设置 租赁物存放详细地址
	 * 
	 * @param detailLocation
	 *            租赁物存放详细地址
	 */
	public void setDetailLocation(String detailLocation) {
		this.detailLocation = detailLocation;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	 */
	public RentSideInfo getRentSideInfo() {
		return rentSideInfo;
	}

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param rentSideInfo
	 *            bare_field_comment
	 */
	public void setRentSideInfo(RentSideInfo rentSideInfo) {
		this.rentSideInfo = rentSideInfo;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param username
	 *            bare_field_comment
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 返回 租赁类型
	 * 
	 * @return 租赁类型
	 */
	public int getRentType() {
		return rentType;
	}

	/**
	 * 设置 租赁类型
	 * 
	 * @param rentType
	 *            租赁类型
	 */
	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	/**
	 * 返回 起始时间
	 * 
	 * @return 起始时间
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 设置 起始时间
	 * 
	 * @param startTime
	 *            起始时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 返回 结束时间
	 * 
	 * @return 结束时间
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置 结束时间
	 * 
	 * @param endTime
	 *            结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 返回 租赁物存放省
	 * 
	 * @return 租赁物存放省
	 */
	public int getProvince() {
		return province;
	}

	/**
	 * 设置 租赁物存放省
	 * 
	 * @param province
	 *            租赁物存放省
	 */
	public void setProvince(int province) {
		this.province = province;
	}

	/**
	 * 返回 租赁物存放市
	 * 
	 * @return 租赁物存放市
	 */
	public int getCity() {
		return city;
	}

	/**
	 * 设置 租赁物存放市
	 * 
	 * @param city
	 *            租赁物存放市
	 */
	public void setCity(int city) {
		this.city = city;
	}

	/**
	 * 返回 保证金总额
	 * 
	 * @return 保证金总额
	 */
	public String getAllMoney() {
		return allMoney;
	}

	/**
	 * 设置 保证金总额
	 * 
	 * @param allMoney
	 *            保证金总额
	 */
	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	/**
	 * 返回 租赁方类型
	 * 
	 * @return 租赁方类型
	 */
	public String getLeaseType() {
		return leaseType;
	}

	/**
	 * 设置 租赁方类型
	 * 
	 * @param leaseType
	 *            租赁方类型
	 */
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	/**
	 * 返回 租赁人信息
	 * 
	 * @return 租赁人信息
	 */
	public RentPersonInfo getRentPersonInfo() {
		return rentPersonInfo;
	}

	/**
	 * 设置 租赁人信息
	 * 
	 * @param rentPersonInfo
	 *            租赁人信息
	 */
	public void setRentPersonInfo(RentPersonInfo rentPersonInfo) {
		this.rentPersonInfo = rentPersonInfo;
	}

	/**
	 * 返回 租赁公司信息
	 * 
	 * @return 租赁公司信息
	 */
	public RentCompanyInfo getRentCompanyInfo() {
		return rentCompanyInfo;
	}

	/**
	 * 设置 租赁公司信息
	 * 
	 * @param rentCompanyInfo
	 *            租赁公司信息
	 */
	public void setRentCompanyInfo(RentCompanyInfo rentCompanyInfo) {
		this.rentCompanyInfo = rentCompanyInfo;
	}

	/**
	 * 返回 子订单
	 * 
	 * @return 子订单
	 */
	public List<SubOrder> getSubOrders() {
		return subOrders;
	}

	/**
	 * 设置 子订单
	 * 
	 * @param subOrders
	 *            子订单
	 */
	public void setSubOrders(List<SubOrder> subOrders) {
		this.subOrders = subOrders;
	}

	/**
	 * 返回 订单状态
	 * 
	 * @return 订单状态
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 设置 订单状态
	 * 
	 * @param orderStatus
	 *            订单状态
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 返回 需要发票
	 * 
	 * @return 需要发票
	 */
	public String getNeedInvoices() {
		return needInvoices;
	}

	/**
	 * 设置 需要发票
	 * 
	 * @param needInvoices
	 *            需要发票
	 */
	public void setNeedInvoices(String needInvoices) {
		this.needInvoices = needInvoices;
	}

	public String getIdCardFrontImg() {
		return idCardFrontImg;
	}
	

	public void setIdCardFrontImg(String idCardFrontImg) {
		this.idCardFrontImg = idCardFrontImg;
	}
	

	public String getIdCardBackImg() {
		return idCardBackImg;
	}
	

	public void setIdCardBackImg(String idCardBackImg) {
		this.idCardBackImg = idCardBackImg;
	}
	

	public String getIdCardHandImg() {
		return idCardHandImg;
	}
	

	public void setIdCardHandImg(String idCardHandImg) {
		this.idCardHandImg = idCardHandImg;
	}
	
	
	//Start: SUNZHE, 2017-03-26
	public String getRentDuraion() {
		return rentDuraion;
	}
	public void setRentDuraion(String rentDuraion) {
		this.rentDuraion = rentDuraion;
	}
	
	public String getTotalDeposit() {
		return totalDeposit;
	}
	public void setTotalDeposit(String totalDeposit) {
		this.totalDeposit = totalDeposit;
	}
	
	public String getTotalInsurance() {
		return totalInsurance;
	}
	public void setTotalInsurance(String totalInsurance) {
		this.totalInsurance = totalInsurance;
	}	
	//End: SUNZHE, 2017-03-26
}
