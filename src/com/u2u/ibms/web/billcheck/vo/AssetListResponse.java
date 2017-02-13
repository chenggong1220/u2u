package com.u2u.ibms.web.billcheck.vo;

public class AssetListResponse {

	private String model;
	private String assetId;
	private String useTime;
	private String repairTime;
	private String amount;
	private String deviceDate;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(String repairTime) {
		this.repairTime = repairTime;
	}

	//Start: 为了在账单详情列表中显示设备运行日期，by SUNZHE, 2017-02-06
	public String getDeviceDate() {
		return deviceDate;
	}

	public void setDeviceDate(String deviceDate) {
		this.deviceDate = deviceDate;
	}
	//End: 详情列表中显示设备运行日期，by SUNZHE, 2017-02-06
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}	

}
