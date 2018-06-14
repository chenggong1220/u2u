package com.u2u.framework.sys.authorize.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @ClassName: SecurityFilter <br>
 * @Description: TODO <br>
 * @date 2015-2-10 下午2:57:46 <br>
 * 
 * @author xuqinghan
 */
@Deprecated
public class TagSercurityInterceptor extends AbstractSecurityInterceptor
{
    
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource()
    {
        return this.securityMetadataSource;
    }
    
    public Class<? extends Object> getSecureObjectClass()
    {
        return FilterInvocation.class;
    }
    
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource()
    {
        return this.securityMetadataSource;
    }
    
    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource)
    {
        this.securityMetadataSource = securityMetadataSource;
    }
    
}
