package com.u2u.framework.jms.mail;

import java.net.URL;

import com.u2u.framework.exception.ServiceMailException;

/**
 * 
 * @ClassName: Mail <br>
 * @Description: 邮件操作对象 <br>
 * @date 2015-3-2 下午1:43:11 <br>
 * 
 * @author Freud
 */
public interface Mail
{
    
    void setSubject(String subject);
    
    void setFrom(String email)
        throws ServiceMailException;
    
    void addTo(String email)
        throws ServiceMailException;
    
    void addTo(String email, String name)
        throws ServiceMailException;
    
    void addReplyTo(String email)
        throws ServiceMailException;
    
    void addReplyTo(String email, String name)
        throws ServiceMailException;
    
    void addCc(String email)
        throws ServiceMailException;
    
    void addCc(String email, String name)
        throws ServiceMailException;
    
    void addBcc(String email)
        throws ServiceMailException;
    
    void addBcc(String email, String name)
        throws ServiceMailException;
    
    void addAttache(URL url, String name, String desc)
        throws ServiceMailException;
    
    void addAttache(String path, String name, String desc)
        throws ServiceMailException;
    
    void addContent(String key, Object o);
    
    void setMsg(String msg)
        throws ServiceMailException;
    
}
