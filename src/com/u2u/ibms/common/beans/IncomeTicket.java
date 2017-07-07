package com.u2u.ibms.common.beans;

import java.sql.Timestamp;

import com.u2u.framework.base.BaseBean;
import com.u2u.framework.util.DateUtil;

public class IncomeTicket extends BaseBean {

	private int id;
	private int incomeId;
	private int contractId;
	private Timestamp openDate;
	@SuppressWarnings("unused")
	private String openDateView;
	private int project;
	private String type;
	private String ticketId;
	private Float amount;
	private Float realAmount;

	public String getOpenDateView() {
		if (openDate == null) {
			return "";
		} else {
			return DateUtil.timestamp2String(openDate, DateUtil.PATTERN_DATE);
		}
	}

	public void setOpenDateView(String openDateView) {
		this.openDateView = openDateView;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public Timestamp getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Float realAmount) {
		this.realAmount = realAmount;
	}

}
