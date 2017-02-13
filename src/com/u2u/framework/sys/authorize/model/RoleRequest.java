package com.u2u.framework.sys.authorize.model;

import com.u2u.framework.base.BaseRequest;

/**
 * @ClassName: BookRequest <br>
 * @Description: TODO <br>
 * @date 2015-3-10 下午1:26:54 <br>
 * 
 * @author Freud
 */
public class RoleRequest extends BaseRequest
{
    
    private int id;
    
    private String rolename;
    
    private String resources;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getRolename()
    {
        return rolename;
    }
    
    public void setRolename(String rolename)
    {
        this.rolename = rolename;
    }
    
    public String getResources()
    {
        return resources;
    }
    
    public void setResources(String resources)
    {
        this.resources = resources;
    }
    
}
