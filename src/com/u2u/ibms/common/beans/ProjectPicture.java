package com.u2u.ibms.common.beans;

import com.u2u.framework.base.BaseBean;

public class ProjectPicture extends BaseBean {

	private int id;
	private int projectId;
	private int pictureIndex;
	private String picture;

	public int getPictureIndex() {
		return pictureIndex;
	}

	public void setPictureIndex(int pictureIndex) {
		this.pictureIndex = pictureIndex;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
