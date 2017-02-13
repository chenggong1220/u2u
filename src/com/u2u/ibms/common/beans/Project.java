package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class Project extends BaseBean {

	private int id;
	private String projectId;
	private int orderId;
	private Order order;

	private boolean required1;
	private String required1Content;
	private boolean required2;
	private String required2Content;
	private boolean required3;
	private String required3Content;
	private boolean required4;
	private String required4Content;
	private boolean required5;
	private String required5Content;
	private boolean required6;
	private String required6Content;

	private String identifyImage;

	private int result;
	private int creditResult;
	private String feedback;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getCreditResult() {
		return creditResult;
	}

	public void setCreditResult(int creditResult) {
		this.creditResult = creditResult;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isRequired1() {
		return required1;
	}

	public void setRequired1(boolean required1) {
		this.required1 = required1;
	}

	public String getRequired1Content() {
		return required1Content;
	}

	public void setRequired1Content(String required1Content) {
		this.required1Content = required1Content;
	}

	public boolean isRequired2() {
		return required2;
	}

	public void setRequired2(boolean required2) {
		this.required2 = required2;
	}

	public String getRequired2Content() {
		return required2Content;
	}

	public void setRequired2Content(String required2Content) {
		this.required2Content = required2Content;
	}

	public boolean isRequired3() {
		return required3;
	}

	public void setRequired3(boolean required3) {
		this.required3 = required3;
	}

	public String getRequired3Content() {
		return required3Content;
	}

	public void setRequired3Content(String required3Content) {
		this.required3Content = required3Content;
	}

	public boolean isRequired4() {
		return required4;
	}

	public void setRequired4(boolean required4) {
		this.required4 = required4;
	}

	public String getRequired4Content() {
		return required4Content;
	}

	public void setRequired4Content(String required4Content) {
		this.required4Content = required4Content;
	}

	public boolean isRequired5() {
		return required5;
	}

	public void setRequired5(boolean required5) {
		this.required5 = required5;
	}

	public String getRequired5Content() {
		return required5Content;
	}

	public void setRequired5Content(String required5Content) {
		this.required5Content = required5Content;
	}

	public boolean isRequired6() {
		return required6;
	}

	public void setRequired6(boolean required6) {
		this.required6 = required6;
	}

	public String getRequired6Content() {
		return required6Content;
	}

	public void setRequired6Content(String required6Content) {
		this.required6Content = required6Content;
	}

	public String getIdentifyImage() {
		return identifyImage;
	}

	public void setIdentifyImage(String identifyImage) {
		this.identifyImage = identifyImage;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
