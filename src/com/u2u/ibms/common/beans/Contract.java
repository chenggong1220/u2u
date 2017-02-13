package com.u2u.ibms.common.beans;

import java.sql.Timestamp;

import com.u2u.framework.base.BaseBean;
import com.u2u.framework.util.DateUtil;

public class Contract extends BaseBean {

	private int id;
	private int orderId;
	private int projectId;
	private String contractId;

	// 发货相关
	private boolean sendStatus;
	private Timestamp sendDate;
	@SuppressWarnings("unused")
	private String sendDateView;
	private boolean sendCheckinStatus;
	private Timestamp sendCheckinDate;
	@SuppressWarnings("unused")
	private String sendCheckinDateView;
	private boolean backStatus;
	private Timestamp backDate;
	@SuppressWarnings("unused")
	private String backDateView;

	// 合同相关
	// 签收
	private boolean checkin;
	private Timestamp checkinDate;
	@SuppressWarnings("unused")
	private String checkinDateView;
	// 签约
	private boolean signoff;
	private Timestamp signoffDate;
	@SuppressWarnings("unused")
	private String signoffDateView;

	private String saveLocation;
	private String status;

	private boolean contractStatus;

	private String orderName;
	private String orderProvince;
	private String orderPerson;
	private String orderMobile;
	private float orderDeposit;
	private float orderLeftDeposit;
	private String location;

	private Project project;
	private Order order;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getSendDateView() {
		if (sendDate != null) {
			return DateUtil.timestamp2String(sendDate, DateUtil.PATTERN_DATE);
		} else {
			return "未发货";
		}
	}

	public void setSendDateView(String sendDateView) {
		this.sendDateView = sendDateView;
	}

	public String getSendCheckinDateView() {
		if (sendCheckinDate != null) {
			return DateUtil.timestamp2String(sendCheckinDate,
					DateUtil.PATTERN_DATE);
		} else {
			return "未收货";
		}
	}

	public void setSendCheckinDateView(String sendCheckinDateView) {
		this.sendCheckinDateView = sendCheckinDateView;
	}

	public String getBackDateView() {
		if (backDate != null) {
			return DateUtil.timestamp2String(backDate, DateUtil.PATTERN_DATE);
		} else {
			return "未归还";
		}
	}

	public void setBackDateView(String backDateView) {
		this.backDateView = backDateView;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public boolean isSendCheckinStatus() {
		return sendCheckinStatus;
	}

	public void setSendCheckinStatus(boolean sendCheckinStatus) {
		this.sendCheckinStatus = sendCheckinStatus;
	}

	public Timestamp getSendCheckinDate() {
		return sendCheckinDate;
	}

	public void setSendCheckinDate(Timestamp sendCheckinDate) {
		this.sendCheckinDate = sendCheckinDate;
	}

	public Timestamp getBackDate() {
		return backDate;
	}

	public void setBackDate(Timestamp backDate) {
		this.backDate = backDate;
	}

	public boolean isContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(boolean contractStatus) {
		this.contractStatus = contractStatus;
	}

	public void setCheckinDateView(String checkinDateView) {
		this.checkinDateView = checkinDateView;
	}

	public void setSignoffDateView(String signoffDateView) {
		this.signoffDateView = signoffDateView;
	}

	public String getCheckinDateView() {
		if (checkinDate != null) {
			return DateUtil
					.timestamp2String(checkinDate, DateUtil.PATTERN_DATE);
		} else {
			return "未签收";
		}
	}

	public String getSignoffDateView() {
		if (signoffDate != null) {
			return DateUtil
					.timestamp2String(signoffDate, DateUtil.PATTERN_DATE);
		} else {
			return "未签约";
		}
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderProvince() {
		return orderProvince;
	}

	public void setOrderProvince(String orderProvince) {
		this.orderProvince = orderProvince;
	}

	public String getOrderPerson() {
		return orderPerson;
	}

	public void setOrderPerson(String orderPerson) {
		this.orderPerson = orderPerson;
	}

	public String getOrderMobile() {
		return orderMobile;
	}

	public void setOrderMobile(String orderMobile) {
		this.orderMobile = orderMobile;
	}

	public float getOrderDeposit() {
		return orderDeposit;
	}

	public void setOrderDeposit(float orderDeposit) {
		this.orderDeposit = orderDeposit;
	}

	public float getOrderLeftDeposit() {
		return orderLeftDeposit;
	}

	public void setOrderLeftDeposit(float orderLeftDeposit) {
		this.orderLeftDeposit = orderLeftDeposit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public boolean isSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(boolean sendStatus) {
		this.sendStatus = sendStatus;
	}

	public boolean isBackStatus() {
		return backStatus;
	}

	public void setBackStatus(boolean backStatus) {
		this.backStatus = backStatus;
	}

	public boolean isCheckin() {
		return checkin;
	}

	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}

	public Timestamp getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Timestamp checkinDate) {
		this.checkinDate = checkinDate;
	}

	public boolean isSignoff() {
		return signoff;
	}

	public void setSignoff(boolean signoff) {
		this.signoff = signoff;
	}

	public Timestamp getSignoffDate() {
		return signoffDate;
	}

	public void setSignoffDate(Timestamp signoffDate) {
		this.signoffDate = signoffDate;
	}

	public String getSaveLocation() {
		return saveLocation;
	}

	public void setSaveLocation(String saveLocation) {
		this.saveLocation = saveLocation;
	}

}
