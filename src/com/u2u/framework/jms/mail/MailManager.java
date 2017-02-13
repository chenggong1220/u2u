package com.u2u.framework.jms.mail;

import com.u2u.framework.exception.ServiceMailException;

/**
 * 
 * @ClassName: MailManager <br>
 * @Description: 邮件管理接口 <br>
 * @date 2015-3-2 下午1:43:27 <br>
 * 
 * @author Freud
 */
public interface MailManager
{
    
    public void sendMail(Mail mail)
        throws ServiceMailException;
    
    public void sendMail(Mail mail, MailTemplate template)
        throws ServiceMailException;
    
    public void sendMail(Mail mail, MailTemplateKey vmTemplate)
        throws ServiceMailException;
    
    public Mail newMailInstance(boolean isHtmlBody)
        throws ServiceMailException;
    
}
