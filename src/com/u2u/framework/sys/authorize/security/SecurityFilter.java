package com.u2u.framework.sys.authorize.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @ClassName: SecurityFilter <br>
 * @Description: TODO <br>
 * @date 2015-2-10 下午2:57:46 <br>
 * 
 * @author xuqinghan
 */
public class SecurityFilter extends AbstractSecurityInterceptor implements Filter
{
    
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }
    
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource()
    {
        return this.securityMetadataSource;
    }
    
    public Class<? extends Object> getSecureObjectClass()
    {
        return FilterInvocation.class;
    }
    
    public void invoke(FilterInvocation fi)
        throws IOException, ServletException
    {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try
        {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }
        finally
        {
            super.afterInvocation(token, null);
        }
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
    
    public void destroy()
    {
    }
    
    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }
    
}
