package com.u2u.framework.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @ClassName: SecureTag <br>
 * @Description: 用于 ibms-security.tld <br>
 * @date 2015-4-27 下午5:17:25 <br>
 * 
 * @author dongming
 */
@Deprecated
public abstract class SecureTag extends TagSupport implements TagContextTag
{
    
    private static final long serialVersionUID = 1L;
    
    // private static final Logger log = LoggerFactory.getLogger(SecureTag.class);
    
    public SecureTag()
    {
    }
    
    protected Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    protected Object getPrincipal()
    {
        Authentication authentication = getAuthentication();
        if (authentication != null)
            return authentication.getPrincipal();
        return null;
    }
    
    // protected boolean isAuthenticated() {
    // Authentication authentication = getAuthentication();
    // Assert.notNull(authentication,"Authentication is not null!");
    // return authentication.isAuthenticated();
    // }
    
    protected void verifyAttributes()
        throws JspException
    {
    }
    
    @Override
    public int doStartTag()
        throws JspException
    {
        verifyAttributes();
        return onDoStartTag();
    }
    
    public abstract int onDoStartTag()
        throws JspException;
    
    /**
     * Make an authorization decision based on a Spring EL expression. See the "Expression-Based Access Control" chapter
     * in Spring Security for details on what expressions can be used.
     * 
     * @return the result of the authorization decision
     * @throws java.io.IOException
     */
    public boolean authorizeUsingAccessExpression(String access)
        throws IOException
    {
        if (SecurityContextHolder.getContext().getAuthentication() == null)
            return false;
        
        SecurityExpressionHandler<FilterInvocation> handler = getExpressionHandler();
        
        Expression accessExpression;
        try
        {
            accessExpression = handler.getExpressionParser().parseExpression(access);
            
        }
        catch (ParseException e)
        {
            IOException ioException = new IOException();
            ioException.initCause(e);
            throw ioException;
        }
        
        return ExpressionUtils.evaluateAsBoolean(accessExpression, createExpressionEvaluationContext(handler));
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    private SecurityExpressionHandler<FilterInvocation> getExpressionHandler()
        throws IOException
    {
        ApplicationContext appContext =
            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        Map<String, SecurityExpressionHandler> handlers = appContext.getBeansOfType(SecurityExpressionHandler.class);
        
        for (SecurityExpressionHandler h : handlers.values())
        {
            if (FilterInvocation.class.equals(GenericTypeResolver.resolveTypeArgument(h.getClass(),
                SecurityExpressionHandler.class)))
                return h;
        }
        
        throw new IOException("No visible WebSecurityExpressionHandler instance could be found in the application "
            + "context. There must be at least one in order to support expressions in JSP 'authorize' tags.");
    }
    
    /**
     * Allows the {@code EvaluationContext} to be customized for variable lookup etc.
     */
    protected EvaluationContext createExpressionEvaluationContext(SecurityExpressionHandler<FilterInvocation> handler)
    {
        FilterInvocation f = new FilterInvocation(getRequest(), getResponse(), new FilterChain()
        {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response)
                throws IOException, ServletException
            {
                throw new UnsupportedOperationException();
            }
        });
        
        return handler.createEvaluationContext(SecurityContextHolder.getContext().getAuthentication(), f);
    }
    
    @Override
    public ServletRequest getRequest()
    {
        return getPageContext().getRequest();
    }
    
    @Override
    public ServletResponse getResponse()
    {
        return getPageContext().getResponse();
    }
    
    @Override
    public HttpSession getSession()
    {
        return getPageContext().getSession();
    }
    
    @Override
    public ServletContext getServletContext()
    {
        return getPageContext().getServletContext();
    }
    
}