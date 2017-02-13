package com.u2u.framework.jms.mail.impl;

import java.io.Serializable;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.u2u.framework.config.AppConfiguration;
import com.u2u.framework.exception.ServiceMailException;
import com.u2u.framework.jms.mail.Mail;

/**
 * 
 * @ClassName: MailImpl <br>
 * @Description: 邮件发送的实现类 <br>
 * @date 2015-3-2 上午11:39:05 <br>
 * 
 * @author Freud
 */
public class MailImpl implements Mail
{
    
    private Map<String, Object> content = new HashMap<String, Object>();
    
    private static final String CHARSET = "UTF-8";
    
    private boolean isHtmlBody;
    
    private HtmlEmail mail;
    
    protected MailImpl(boolean isHtmlBody)
        throws ServiceMailException
    {
        try
        {
            AppConfiguration appconfig = AppConfiguration.getInstance();
            String host = appconfig.getString("app.mail.server.host");
            // String protocol = appconfig.getString("app.mail.server.protocol");
            String username = appconfig.getString("app.default.username");
            String password = appconfig.getString("app.default.password");
            int port = appconfig.getInt("app.mail.server.port");
            boolean isSecurity = appconfig.getBoolean("app.mail.server.isSecurity");
            boolean isDebug = appconfig.getBoolean("app.mail.server.isDebug");
            
            this.isHtmlBody = isHtmlBody;
            
            mail = new HtmlEmail();
            mail.setCharset(CHARSET);
            mail.addHeader("X-Mailer", "WEB MAILER 1.0.1");
            mail.setDebug(isDebug);
            
            mail.setHostName(host);
            mail.setAuthentication(username, password);
            mail.setFrom(appconfig.getString("app.system.email"));
            mail.setSmtpPort(port);
            mail.setTLS(true);
            
            if (isSecurity)
                mail.setSSL(true);
        }
        catch (EmailException e)
        {
            throw new ServiceMailException(MessageFormat.format("邮件发送初始化失败[{0}]", e.getMessage()));
        }
    }
    
    HtmlEmail getMail()
    {
        return mail;
    }
    
    /**
     * 
     * <p>
     * Discription:[添加附件]
     * </p>
     * 
     * @param url
     * @param name
     * @param desc
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addAttache(URL url, String name, String desc)
        throws ServiceMailException
    {
        try
        {
            mail.attach(url, name, desc);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加附件失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[添加附件]
     * </p>
     * 
     * @param path
     * @param name
     * @param desc
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addAttache(String path, String name, String desc)
        throws ServiceMailException
    {
        try
        {
            
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(path);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setName(name);
            attachment.setDescription(desc);
            
            mail.attach(attachment);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加附件失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[添加秘抄]
     * </p>
     * 
     * @param email
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addBcc(String email)
        throws ServiceMailException
    {
        try
        {
            mail.addBcc(email);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加秘抄失败原因为[{0}]", e.getMessage()));
        }
        
    }
    
    /**
     * 
     * <p>
     * Discription:[添加秘抄]
     * </p>
     * 
     * @param email
     * @param name
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addBcc(String email, String name)
        throws ServiceMailException
    {
        try
        {
            mail.addBcc(email, name);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加秘抄失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[添加抄送]
     * </p>
     * 
     * @param email
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addCc(String email)
        throws ServiceMailException
    {
        
        try
        {
            mail.addCc(email);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加抄送失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[添加抄送]
     * </p>
     * 
     * @param email
     * @param name
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addCc(String email, String name)
        throws ServiceMailException
    {
        try
        {
            mail.addBcc(email, name);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加抄送失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[添加邮件内容的Key,Value值]
     * </p>
     * 
     * @param key
     * @param o
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addContent(String key, Object o)
    {
        content.put(key, o);
    }
    
    /**
     * 
     * <p>
     * Discription:[获得内容的键值对]
     * </p>
     * 
     * @return
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public Map<String, Object> getContentMap()
    {
        return content;
    }
    
    /**
     * 
     * <p>
     * Discription:[添加返回人信息]
     * </p>
     * 
     * @param email
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addReplyTo(String email)
        throws ServiceMailException
    {
        try
        {
            mail.addReplyTo(email);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加回复失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[添加返回人信息]
     * </p>
     * 
     * @param email
     * @param name
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addReplyTo(String email, String name)
        throws ServiceMailException
    {
        try
        {
            mail.addReplyTo(email, name);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加回复失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[设置发送到联系人]
     * </p>
     * 
     * @param email
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addTo(String email)
        throws ServiceMailException
    {
        try
        {
            mail.addTo(email);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加发送到失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[设置发送到联系人]
     * </p>
     * 
     * @param email
     * @param name
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void addTo(String email, String name)
        throws ServiceMailException
    {
        try
        {
            mail.addBcc(email, name);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加发送到失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[清除Content中的键值对]
     * </p>
     * 
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void clear()
    {
        content.clear();
    }
    
    /**
     * 
     * <p>
     * Discription:[获得主题]
     * </p>
     * 
     * @return
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public String getSubject()
    {
        return mail.getSubject();
    }
    
    /**
     * 
     * <p>
     * Discription:[设置消息发送人]
     * </p>
     * 
     * @param email
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void setFrom(String email)
        throws ServiceMailException
    {
        try
        {
            mail.setFrom(email);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("设置发送人失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[设置消息内容]
     * </p>
     * 
     * @param msg
     * @throws ServiceMailException
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void setMsg(String msg)
        throws ServiceMailException
    {
        try
        {
            if (isHtmlBody)
                mail.setHtmlMsg(msg);
            else
                mail.setTextMsg(msg);
        }
        catch (Exception e)
        {
            throw new ServiceMailException(MessageFormat.format("添加发送消息失败原因为[{0}]", e.getMessage()));
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[获得标题]
     * </p>
     * 
     * @param subject
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public void setSubject(String subject)
    {
        mail.setSubject(subject);
    }
    
    /**
     * 
     * <p>
     * Discription:[获得ID]
     * </p>
     * 
     * @return
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    public Serializable getId()
    {
        return null;
    }
    
}
