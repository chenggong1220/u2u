package com.u2u.framework.sys.authorize.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Role <br>
 * @Description: 角色类 <br>
 * @date 2015年1月14日 下午2:24:01 <br>
 * 
 * @author Freud
 */
public class Role implements Serializable
{
    
    /**
     * <p>
     * Discription:[字段功能描述]
     * </p>
     */
    private static final long serialVersionUID = -7398033380596098915L;
    
    private int id;
    
    private String roleName;
    
    private List<Resource> resources = new ArrayList<>();
    
    public Role()
    {
        super();
    }
    
    public Role(int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return String roleName.
     */
    public String getRoleName()
    {
        return roleName;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param roleName The roleName to set.
     */
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return List<Resource> resources.
     */
    public List<Resource> getResources()
    {
        if (resources == null)
        {
            resources = new ArrayList<Resource>();
        }
        return resources;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param resources The resources to set.
     */
    public void setResources(List<Resource> resources)
    {
        this.resources = resources;
    }
    
}
