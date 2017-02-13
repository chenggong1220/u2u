package com.u2u.ibms.common.beans;

import java.sql.Timestamp;

import com.u2u.framework.base.BaseBean;
import com.u2u.framework.util.DateUtil;

public class BillCheck extends BaseBean {

	private int id;
	private int orderId;
	private int contractId;
	private Integer userId;
	private int currentTerm;
	private String currentTermDate;
	private int terms;
	private Timestamp outdate;
	@SuppressWarnings("unused")
	private String outdateView;
	private Timestamp enddate;
	@SuppressWarnings("unused")
	private String enddateView;
	private float useTime;
	private float repairTime;
	private float rentAmount;
	private float interest;
	private float allAmount;
	private int rentType;
	private int payStatus;
	private float payAmount;
	private boolean status;
	
	private Order order;
	private Contract contract;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCurrentTermDate() {
		return currentTermDate;
	}

	public void setCurrentTermDate(String currentTermDate) {
		this.currentTermDate = currentTermDate;
	}

	public float getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(float payAmount) {
		this.payAmount = payAmount;
	}

	public String getOutdateView() {
		if (outdate == null) {
			return "";
		} else {
			return DateUtil.timestamp2String(this.getOutdate(),
					DateUtil.PATTERN_DATE);
		}
	}

	public void setOutdateView(String outdateView) {
		this.outdateView = outdateView;
	}

	public String getEnddateView() {
		if (enddate == null) {
			return "";
		} else {
			return DateUtil.timestamp2String(this.getEnddate(),
					DateUtil.PATTERN_DATE);
		}
	}

	public void setEnddateView(String enddateView) {
		this.enddateView = enddateView;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	 */
	public int getCurrentTerm() {
		return currentTerm;
	}

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param currentTerm
	 *            bare_field_comment
	 */
	public void setCurrentTerm(int currentTerm) {
		this.currentTerm = currentTerm;
	}

	public int getTerms() {
		return terms;
	}

	public void setTerms(int terms) {
		this.terms = terms;
	}

	public Timestamp getOutdate() {
		return outdate;
	}

	public void setOutdate(Timestamp outdate) {
		this.outdate = outdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public float getUseTime() {
		return useTime;
	}

	public void setUseTime(float useTime) {
		this.useTime = useTime;
	}

	public float getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(float repairTime) {
		this.repairTime = repairTime;
	}

	public float getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(float rentAmount) {
		this.rentAmount = rentAmount;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public float getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(float allAmount) {
		this.allAmount = allAmount;
	}

	public int getRentType() {
		return rentType;
	}

	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

}
