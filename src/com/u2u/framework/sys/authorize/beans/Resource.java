package com.u2u.framework.sys.authorize.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @ClassName: Navigation <br>
 * @Description: 导航,菜单实体类. <br>
 * @date 2015-2-5 下午02:18:12 <br>
 * 
 * @author Dean
 */
public class Resource implements Serializable {
	/**
	 * <p>
	 * Discription:[字段功能描述]
	 * </p>
	 */
	private static final long serialVersionUID = -2430523293237252486L;

	private int id;

	private int parentId;

	private int level;

	private String imageUrl;

	private String link;

	private String description;

	private String updateUser;

	private String updateTime;

	private boolean ifValid;

	private int priority;

	private List<Role> roles;

	private List<Resource> resources;

	public Resource() {
		super();
	}

	public Resource(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return int parentId.
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param parentId
	 *            The parentId to set.
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String imageUrl.
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param imageUrl
	 *            The imageUrl to set.
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String updateUser.
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param updateUser
	 *            The updateUser to set.
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String updateTime.
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param updateTime
	 *            The updateTime to set.
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return boolean ifValid.
	 */
	public boolean isIfValid() {
		return ifValid;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param ifValid
	 *            The ifValid to set.
	 */
	public void setIfValid(boolean ifValid) {
		this.ifValid = ifValid;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return List<Role> roles.
	 */
	@JsonBackReference
	public List<Role> getRoles() {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		return roles;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param roles
	 *            The roles to set.
	 */
	@JsonBackReference
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return List<Resource> resources.
	 */
	@JsonBackReference
	public List<Resource> getResources() {
		if (resources == null) {
			resources = new ArrayList<Resource>();
		}
		return resources;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param resources
	 *            The resources to set.
	 */
	@JsonBackReference
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return int priority.
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param priority
	 *            The priority to set.
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return long serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
