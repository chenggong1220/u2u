package com.u2u.ibms.web.project.bean;

public class OrderStatistics {

	private float amount;
	private int date;
	private float deposit;
	private float rentAmount;
	private float rentSumAmount;
	private float depositRatio;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public float getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(float rentAmount) {
		this.rentAmount = rentAmount;
	}

	public float getRentSumAmount() {
		return rentSumAmount;
	}

	public void setRentSumAmount(float rentSumAmount) {
		this.rentSumAmount = rentSumAmount;
	}

	public float getDepositRatio() {
		return depositRatio;
	}

	public void setDepositRatio(float depositRatio) {
		this.depositRatio = depositRatio;
	}

}
