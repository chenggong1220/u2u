package com.u2u.framework.sys.authorize.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.framework.sys.authorize.beans.Resource;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.service.AuthorizeService;

/**
 * @ClassName: SecurityMetadataSourceService <br>
 * @Description: TODO <br>
 * @date 2015-2-10 下午1:55:19 <br>
 * 
 * @author xuqinghan
 */
@Deprecated
public class TagSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource
{
    private AuthorizeService authorizeService;
    
    // According to a URL, Find out permission configuration of this URL.
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
        throws IllegalArgumentException
    {
        List<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
        Resource resourcetmp = null;
        String id = (String)object;
        try
        {
            resourcetmp = authorizeService.getResourceById(Integer.parseInt(id));
        }
        catch (ServiceBusinessException e)
        {
            e.printStackTrace();
        }
        if (resourcetmp != null && resourcetmp.getRoles() != null && !resourcetmp.getRoles().isEmpty())
        {
            for (Role role : resourcetmp.getRoles())
            {
                attrs.add(new SecurityConfig(role.getRoleName()));
            }
        }
        return attrs;
    }
    
    public boolean supports(Class<?> clazz)
    {
        return true;
    }
    
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }
    
    public void setAuthorizeService(AuthorizeService authorizeService)
    {
        this.authorizeService = authorizeService;
    }
    
}
