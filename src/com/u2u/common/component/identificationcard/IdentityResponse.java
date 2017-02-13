package com.u2u.common.component.identificationcard;

public class IdentityResponse {

	private int error_code;
	private String reason;
	private IdentityResponseResult result;

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public IdentityResponseResult getResult() {
		return result;
	}

	public void setResult(IdentityResponseResult result) {
		this.result = result;
	}

}
