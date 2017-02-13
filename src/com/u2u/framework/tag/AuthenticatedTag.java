package com.u2u.framework.tag;

import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.u2u.framework.spring.SpringContextHolder;
import com.u2u.framework.sys.authorize.security.TagSercurityInterceptor;

/**
 * @ClassName: AuthenticatedTag <br>
 * @Description: 用于 ibms-security.tld <br>
 * @date 2015-4-27 下午5:18:58 <br>
 * 
 * @author dongming
 */
@Deprecated
public class AuthenticatedTag extends SecureTag
{
    /**
     * <p>
     * Discription:[字段功能描述]
     * </p>
     */
    private static final long serialVersionUID = 4190809006374982486L;
    
    //private static final Logger log = Logger.getLogger(AuthenticatedTag.class);
    
    private String access;
    
    private String id;
    
    private String name;
    
    private PageContext pageContext;
    
    private TagSercurityInterceptor securityInterceptor;
    
    /**
     * <p>
     * Discription:[根据资源id判断标签内容是否显示]
     * </p>
     */
    
    public AuthenticatedTag()
    {
        
    }
    
    @Override
    public int onDoStartTag()
        throws JspException
    {
        if ((SecurityContextHolder.getContext() == null)
            || !(SecurityContextHolder.getContext() instanceof SecurityContext)
            || (SecurityContextHolder.getContext().getAuthentication() == null))
            return Tag.EVAL_PAGE;
        securityInterceptor = SpringContextHolder.getBean("tagSercurityInterceptor");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        boolean isFullyAuthenticated = isAllowed(id, name, authentication);
        // isFullyAuthenticated = false;
        if (isFullyAuthenticated)
            return EVAL_BODY_INCLUDE;
        else
            return SKIP_BODY;
    }
    
    @Override
    public void setPageContext(PageContext pageContext)
    {
        this.pageContext = pageContext;
    }
    
    @Override
    public PageContext getPageContext()
    {
        return pageContext;
    }
    
    public String getAccess()
    {
        return access;
    }
    
    public void setAccess(String access)
    {
        this.access = access;
    }
    
    @Override
    public String getId()
    {
        return id;
    }
    
    @Override
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean isAllowed(String id, String name, Authentication authentication)
    {
        Collection<ConfigAttribute> attrs = securityInterceptor.obtainSecurityMetadataSource().getAttributes(id);
        
        if (attrs == null)
        {
            if (securityInterceptor.isRejectPublicInvocations())
                return false;
        }
        if (authentication == null)
            return false;
        try
        {
            securityInterceptor.getAccessDecisionManager().decide(authentication, id, attrs);
        }
        catch (AccessDeniedException unauthorized)
        {
            
            return false;
        }
        
        return true;
    }
    
}