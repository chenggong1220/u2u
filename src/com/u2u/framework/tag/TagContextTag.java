package com.u2u.framework.tag;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 * @ClassName: TagContextTag <br>
 * @Description: 用于 ibms-security.tld <br>
 * @date 2015-4-27 下午5:18:48 <br>
 * 
 * @author dongming
 */
@Deprecated
public interface TagContextTag
{
    
    /**
     * This method allows subclasses to provide a way to access the ServletRequest according to the rendering
     * technology.
     */
    public ServletRequest getRequest();
    
    /**
     * This method allows subclasses to provide a way to access the ServletResponse according to the rendering
     * technology.
     */
    public ServletResponse getResponse();
    
    /**
     * This method allows subclasses to provide a way to access the HttpSession according to the rendering technology.
     */
    public HttpSession getSession();
    
    /**
     * This method allows subclasses to provide a way to access the ServletContext according to the rendering
     * technology.
     */
    public ServletContext getServletContext();
    
    /**
     * 由具体标签提供页面上下文
     * 
     * @return
     */
    public PageContext getPageContext();
    
}