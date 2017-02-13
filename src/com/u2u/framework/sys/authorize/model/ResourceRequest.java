package com.u2u.framework.sys.authorize.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ResourceRequest <br>
 * @Description: TODO <br>
 * @date 2015-3-25 下午5:46:38 <br>
 * 
 * @author Freud
 */
public class ResourceRequest {

	private int id;

	private int priority;

	private Integer parentsResource;

	private Integer parentsLevel;

	private String resourceType;

	private String resourceName;

	private String link;

	private String imageUrl;

	private boolean validate;

	private List<ResourceRequest> resources;

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
	 * @return String id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param id
	 *            The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String parentsResource.
	 */
	public Integer getParentsResource() {
		return parentsResource;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param parentsResource
	 *            The parentsResource to set.
	 */
	public void setParentsResource(Integer parentsResource) {
		this.parentsResource = parentsResource;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String resourceType.
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param resourceType
	 *            The resourceType to set.
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String resourceName.
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param resourceName
	 *            The resourceName to set.
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return String link.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param link
	 *            The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
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
	 * @return boolean validate.
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param validate
	 *            The validate to set.
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return List<ResourceRequest> resources.
	 */
	public List<ResourceRequest> getResources() {
		if (resources == null) {
			resources = new ArrayList<ResourceRequest>();
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
	public void setResources(List<ResourceRequest> resources) {
		this.resources = resources;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return Integer parentsLevel.
	 */
	public Integer getParentsLevel() {
		return parentsLevel;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param parentsLevel
	 *            The parentsLevel to set.
	 */
	public void setParentsLevel(Integer parentsLevel) {
		this.parentsLevel = parentsLevel;
	}

}
