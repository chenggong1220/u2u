package com.u2u.framework.jms.mail.impl;

import java.text.MessageFormat;

import org.apache.commons.mail.HtmlEmail;

import com.u2u.framework.config.AppConfiguration;
import com.u2u.framework.exception.ServiceMailException;
import com.u2u.framework.jms.mail.Mail;
import com.u2u.framework.jms.mail.MailManager;
import com.u2u.framework.jms.mail.MailTemplate;
import com.u2u.framework.jms.mail.MailTemplateKey;
import com.u2u.framework.util.FreeMarkerUtil;

/**
 * 
 * @ClassName: MailManagerImpl <br>
 * @Description: Mail管理实现类 <br>
 * @date 2015-3-2 下午1:34:22 <br>
 * 
 * @author Freud
 */
public class MailManagerImpl implements MailManager
{
    
    /**
     * 
     * <p>
     * Discription:[新建一个Mail对象]
     * </p>
     * 
     * @param isHtmlBody
     * @return
     * @author:[Freud]
     * @throws ServiceMailException
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public Mail newMailInstance(boolean isHtmlBody)
        throws ServiceMailException
    {
        return new MailImpl(isHtmlBody);
    }
    
    /**
     * 
     * <p>
     * Discription:[发送邮件]
     * </p>
     * 
     * @param mail
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void sendMail(Mail mail)
        throws ServiceMailException
    {
        try
        {
            if (mail == null)
                return;
            MailImpl mailImpl = (MailImpl)mail;
            HtmlEmail htmlEmail = mailImpl.getMail();
            
            System.out.println("Send mail: " + htmlEmail.getSubject());
            htmlEmail.send();
            mailImpl.clear();
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("发送邮件失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[发送邮件]
     * </p>
     * 
     * @param mail
     * @param template
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void sendMail(Mail mail, MailTemplate template)
        throws ServiceMailException
    {
        try
        {
            if (mail == null || template == null || template.getBody() == null)
                return;
            MailImpl mailImpl = (MailImpl)mail;
            
            String subject = FreeMarkerUtil.template2String(template.getSubject(), mailImpl.getContentMap(), false);
            String msg = FreeMarkerUtil.template2String(template.getBody(), mailImpl.getContentMap(), false);
            mailImpl.setSubject(subject);
            mailImpl.setMsg(msg);
            
            System.out.println("Send mail: " + subject);
            mailImpl.getMail().send();
            mailImpl.clear();
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("发送邮件失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[发送邮件]
     * </p>
     * 
     * @param mail
     * @param vmTemplate
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void sendMail(Mail mail, MailTemplateKey vmTemplate)
        throws ServiceMailException
    {
        MailTemplate template = getTemplateByName(vmTemplate.toString());
        this.sendMail(mail, template);
    }
    
    /**
     * 
     * <p>
     * Discription:[通过名字获得Template的对象]
     * </p>
     * 
     * @param name
     * @return
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    private MailTemplate getTemplateByName(String name)
    {
        AppConfiguration config = AppConfiguration.getInstance();
        return config.getMailTemplate(name);
    }
}
