package com.u2u.framework.sys.authorize.beans;

import java.io.Serializable;

/**
 * @ClassName: RoleNavigation <br>
 * @Description: TODO <br>
 * @date 2015-3-19 下午12:41:36 <br>
 * 
 * @author Freud
 */
public class RoleResource implements Serializable
{
    
    /**
     * <p>
     * Discription:[字段功能描述]
     * </p>
     */
    private static final long serialVersionUID = -3207159858858350106L;
    
    private int id;
    
    private int roleId;
    
    private int resourceId;
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return int id.
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param id The id to set.
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return int roleId.
     */
    public int getRoleId()
    {
        return roleId;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param roleId The roleId to set.
     */
    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return int resourceId.
     */
    public int getResourceId()
    {
        return resourceId;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param resourceId The resourceId to set.
     */
    public void setResourceId(int resourceId)
    {
        this.resourceId = resourceId;
    }
    
}
