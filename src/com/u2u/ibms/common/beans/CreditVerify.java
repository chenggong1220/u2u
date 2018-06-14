package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class CreditVerify extends BaseBean {

	private int id;
	private int projectId;
	private int creditRequired1;
	private String creditRequired1Name;
	private int creditRequired2;
	private String creditRequired2Name;
	private int creditRequired3;
	private String creditRequired3Name;
	private String required1Content;
	private String required2Content;
	private String required3Content;
	private String required4Content;
	private String required6Content;

	public String getCreditRequired1Name() {
		return creditRequired1Name;
	}

	public void setCreditRequired1Name(String creditRequired1Name) {
		this.creditRequired1Name = creditRequired1Name;
	}

	public String getCreditRequired2Name() {
		return creditRequired2Name;
	}

	public void setCreditRequired2Name(String creditRequired2Name) {
		this.creditRequired2Name = creditRequired2Name;
	}

	public String getCreditRequired3Name() {
		return creditRequired3Name;
	}

	public void setCreditRequired3Name(String creditRequired3Name) {
		this.creditRequired3Name = creditRequired3Name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCreditRequired1() {
		return creditRequired1;
	}

	public void setCreditRequired1(int creditRequired1) {
		this.creditRequired1 = creditRequired1;
	}

	public int getCreditRequired2() {
		return creditRequired2;
	}

	public void setCreditRequired2(int creditRequired2) {
		this.creditRequired2 = creditRequired2;
	}

	public int getCreditRequired3() {
		return creditRequired3;
	}

	public void setCreditRequired3(int creditRequired3) {
		this.creditRequired3 = creditRequired3;
	}

	public String getRequired1Content() {
		return required1Content;
	}

	public void setRequired1Content(String required1Content) {
		this.required1Content = required1Content;
	}

	public String getRequired2Content() {
		return required2Content;
	}

	public void setRequired2Content(String required2Content) {
		this.required2Content = required2Content;
	}

	public String getRequired3Content() {
		return required3Content;
	}

	public void setRequired3Content(String required3Content) {
		this.required3Content = required3Content;
	}

	public String getRequired4Content() {
		return required4Content;
	}

	public void setRequired4Content(String required4Content) {
		this.required4Content = required4Content;
	}

	public String getRequired6Content() {
		return required6Content;
	}

	public void setRequired6Content(String required6Content) {
		this.required6Content = required6Content;
	}

}
