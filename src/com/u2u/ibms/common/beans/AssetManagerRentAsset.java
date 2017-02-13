package com.u2u.ibms.common.beans;

import java.sql.Timestamp;

import com.u2u.framework.base.BaseBean;
import com.u2u.framework.util.DateUtil;

public class AssetManagerRentAsset extends BaseBean {

	private int id;
	private int orderId;
	private int assetId;

	private Timestamp rentDate;
	@SuppressWarnings("unused")
	private String rentDateView;
	private boolean receiveStatus;
	private Timestamp receiveDate;
	@SuppressWarnings("unused")
	private String receiveDateView;

	private Asset asset;

	private Order order;

	public Timestamp getRentDate() {
		return rentDate;
	}

	public void setRentDate(Timestamp rentDate) {
		this.rentDate = rentDate;
	}

	public String getRentDateView() {
		if (rentDate != null) {
			return DateUtil.timestamp2String(rentDate, DateUtil.PATTERN_DATE);
		} else {
			return "";
		}
	}

	public void setRentDateView(String rentDateView) {
		this.rentDateView = rentDateView;
	}

	public String getReceiveDateView() {
		if (receiveDate != null) {
			return DateUtil
					.timestamp2String(receiveDate, DateUtil.PATTERN_DATE);
		} else {
			return "";
		}
	}

	public void setReceiveDateView(String receiveDateView) {
		this.receiveDateView = receiveDateView;
	}

	public boolean isReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(boolean receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public Timestamp getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Timestamp receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
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

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

}
