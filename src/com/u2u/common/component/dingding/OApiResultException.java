package com.u2u.common.component.dingding;

public class OApiResultException extends OApiException {

	/**
	 * <p>Discription:[字段功能描述]</p>
	 */
	private static final long serialVersionUID = -7175031279519027624L;
	public static final int ERR_RESULT_RESOLUTION = -2;
	
	public OApiResultException(String field) {
		super(ERR_RESULT_RESOLUTION, "Cannot resolve field " + field + " from oapi resonpse");
	}

}
