package com.u2u.ibms.common.util.contract.vo;

import java.io.File;
import java.util.List;

public class ContractFileVo {

	private int rentType;
	private String contractId;
	private File logo;
	private String operator;		//甲方经办人
	private String operatorMobile;	//甲方经办人电话

	private List<ContractRentAsset> assets;

	private ContractConstructor contractConstructor;
	private String transforAddress;
	private String useAddress;
	private String deposit;
	private String insuranceServeAmount;
	private String amountTotal;

	private ContractYifang contractYifang;

	private File ziti;
	private File sealFile;
	
	//Start: Added for GM Signature, by SUNZHE, 2017-01-11
	private File signatureFile;		
	public File getSignatureFile() {
		return signatureFile;
	}

	public void setSignatureFile(File signatureFile) {
		this.signatureFile = signatureFile;
	}
	//End: Added for GM Signature, by SUNZHE, 2017-01-11	

	public File getSealFile() {
		return sealFile;
	}

	public void setSealFile(File sealFile) {
		this.sealFile = sealFile;
	}	
	
	public File getZiti() {
		return ziti;
	}

	public void setZiti(File ziti) {
		this.ziti = ziti;
	}

	public ContractYifang getContractYifang() {
		return contractYifang;
	}

	public void setContractYifang(ContractYifang contractYifang) {
		this.contractYifang = contractYifang;
	}

	public int getRentType() {
		return rentType;
	}

	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public ContractConstructor getContractConstructor() {
		return contractConstructor;
	}

	public void setContractConstructor(ContractConstructor contractConstructor) {
		this.contractConstructor = contractConstructor;
	}

	public String getTransforAddress() {
		return transforAddress;
	}

	public void setTransforAddress(String transforAddress) {
		this.transforAddress = transforAddress;
	}

	public String getUseAddress() {
		return useAddress;
	}

	public void setUseAddress(String useAddress) {
		this.useAddress = useAddress;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getInsuranceServeAmount() {
		return insuranceServeAmount;
	}

	public void setInsuranceServeAmount(String insuranceServeAmount) {
		this.insuranceServeAmount = insuranceServeAmount;
	}

	public String getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(String amountTotal) {
		this.amountTotal = amountTotal;
	}

	public List<ContractRentAsset> getAssets() {
		return assets;
	}

	public void setAssets(List<ContractRentAsset> assets) {
		this.assets = assets;
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

}
