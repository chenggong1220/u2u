package com.u2u.framework.jms.mail;

/**
 * 
 * @ClassName: MailTemplate <br>
 * @Description: 邮件模版对象 <br>
 * @date 2015-3-2 下午1:43:43 <br>
 * 
 * @author Freud
 */
public class MailTemplate
{
    
    private String name;
    
    private String subject;
    
    private String body;
    
    public MailTemplate(String name, String subject, String body)
    {
        this.name = name;
        this.subject = subject;
        this.body = body;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSubject()
    {
        return subject;
    }
    
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    public String getBody()
    {
        return body;
    }
    
    public void setBody(String body)
    {
        this.body = body;
    }
    
}
