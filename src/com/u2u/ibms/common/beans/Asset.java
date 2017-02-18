package com.u2u.ibms.common.beans;

import java.sql.Timestamp;

import com.u2u.framework.base.BaseBean;
import com.u2u.ibms.web.shop.bean.Shop;

/**
 * 设备
 * 
 * @author Freud
 * 
 */
public class Asset extends BaseBean {

	// 设备ID
	public int id;

	// 设备类型
	private int assetTypeId;
	// 编码
	private String code;

	private int shopId;
	// 省
	private int provinceId;
	// 市
	private int cityId;
	private String province;
	private String city;
	// 使用时长
	private int useTime;
	// 出租
	private int rent;
	// location
	private String location;
	// 加密狗
	private String softDog;
	// 是否保险
	private boolean insurance;
	// 保险单号
	private String insuranceId;
	// 保险金额
	private Float insuranceAmount;
	// 保险开始时间
	private Timestamp insuranceStart;
	// 保险结束时间
	private Timestamp insuranceEnd;

	private AssetType assetType;

	private Shop shop;
	
	private String contractId;
	private String rentPersonType;
	private String compPersonName;
	private String assetLocation;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
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

	public String getSoftDog() {
		return softDog;
	}

	public void setSoftDog(String softDog) {
		this.softDog = softDog;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public Float getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(Float insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Timestamp getInsuranceStart() {
		return insuranceStart;
	}

	public void setInsuranceStart(Timestamp insuranceStart) {
		this.insuranceStart = insuranceStart;
	}

	public Timestamp getInsuranceEnd() {
		return insuranceEnd;
	}

	public void setInsuranceEnd(Timestamp insuranceEnd) {
		this.insuranceEnd = insuranceEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(int assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getUseTime() {
		return useTime;
	}

	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	//Start: for 租赁物明细报表，SUNZHE, 2017-02-15
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	public String getRentPersonType() {
		return rentPersonType;
	}

	public void setRentPersonType(String rentPersonType) {
		this.rentPersonType = rentPersonType;
	}	
	
	public String getCompPersonName() {
		return compPersonName;
	}

	public void setCompPersonName(String compPersonName) {
		this.compPersonName = compPersonName;
	}		
	
	public String getAssetLocation() {
		return assetLocation;
	}

	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}
	//End: for 租赁物明细报表，SUNZHE, 2017-02-15
}
