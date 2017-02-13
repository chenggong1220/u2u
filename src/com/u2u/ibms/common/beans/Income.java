package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class Income extends BaseBean {

	private int id;
	private int orderId;
	private int billId;
	private String customer;
	private int type;
	private float amount;
	private float payAmount;
	private boolean invoice;
	private boolean invoiceStatus;

	private Contract contract;

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(float payAmount) {
		this.payAmount = payAmount;
	}

	public boolean isInvoice() {
		return invoice;
	}

	public void setInvoice(boolean invoice) {
		this.invoice = invoice;
	}

	public boolean isInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(boolean invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

}
