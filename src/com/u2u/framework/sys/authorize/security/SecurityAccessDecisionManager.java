package com.u2u.framework.sys.authorize.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName: SecurityAccessDecisionManager <br>
 * @Description: TODO <br>
 * @date 2015-2-10 下午2:31:22 <br>
 * 
 * @author xuqinghan
 */
public class SecurityAccessDecisionManager implements AccessDecisionManager
{
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
        throws AccessDeniedException, InsufficientAuthenticationException
    {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
        {
            throw new AccessDeniedException("no right");
        }
        if (configAttributes == null)
        {
            return;
        }
        
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while (ite.hasNext())
        {
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig)ca).getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities())
            {
                if (("ROLE_ADMIN").equalsIgnoreCase(ga.getAuthority()) || needRole.equals(ga.getAuthority()))
                { // ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }
    
    public boolean supports(ConfigAttribute attribute)
    {
        // TODO Auto-generated method stub
        return true;
    }
    
    public boolean supports(Class<?> clazz)
    {
        return true;
    }
    
}
