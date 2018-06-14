package com.u2u.framework.webscoket;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;

/**
 * @ClassName: WebSocketBean <br>
 * @Description: The container of websocket connection.<br>
 * @date 2015-2-10 下午03:10:02 <br>
 * 
 * @author Dean
 */
public class WebSocketBean
{
    private Session session;
    
    private final Set<String> callBackMethods = new CopyOnWriteArraySet<String>();
    
    private final Set<TopicVO> topics = new CopyOnWriteArraySet<TopicVO>();
    
    private String userId;
    
    private String roleId;
    
    public WebSocketBean()
    {
        callBackMethods.add("ibms_system_alarm_list");
        callBackMethods.add("ibms_system_alarm_list_remove");
        callBackMethods.add("ibms_system_alarm_summary_size");
    }
    
    public void cleanBackMethods()
    {
        for (final String entry : callBackMethods)
        {
            if (entry.startsWith("$(window.parent.document)"))
            {
                callBackMethods.remove(entry);
            }
        }
        
    }
    
    public boolean containTopic(String topicName)
    {
        
        for (TopicVO thistopic : topics)
        {
            if (thistopic.getName().equals(topicName))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean addTopic(String topicName, ResourceVO[] assets)
    {
        for (TopicVO thistopic : topics)
        {
            if (thistopic.getName().equals(topicName))
            {
                thistopic.getResources().clear();
                thistopic.getResources().addAll(Arrays.asList(assets));
                return true;
            }
        }
        return false;
    }
    
    public Set<String> getCallBackMethods()
    {
        return callBackMethods;
    }
    
    public String getRoleId()
    {
        return roleId;
    }
    
    public Session getSession()
    {
        return session;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setRoleId(final String roleId)
    {
        this.roleId = roleId;
    }
    
    public void setSession(final Session session)
    {
        this.session = session;
    }
    
    public void setUserId(final String userId)
    {
        this.userId = userId;
    }
    
    public Set<TopicVO> getTopics()
    {
        return topics;
    }
    
}
