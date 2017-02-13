package com.u2u.framework.webscoket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.u2u.framework.config.SysConstants;
import com.u2u.framework.util.ArrayUtils;
import com.u2u.framework.util.LogUtil;

/**
 * @ClassName: WebSocketUtil <br>
 * @Description: For user to push message. <br>
 * @date 2015-2-10 下午03:11:29 <br>
 * 
 * @author Dean
 */
public class WebSocketUtil
{
    /** 推送所注册的连接 */
    public static final ConcurrentMap<Object, WebSocketBean> connections =
        new ConcurrentHashMap<Object, WebSocketBean>();
    
    /** The Logger */
    private static final Log log = LogFactory.getLog(WebSocketUtil.class);
    
    /**
     * 
     * <p>
     * Discription:[推送到前台展示]
     * </p>
     * 
     * @param callBackMethod
     * @param msg
     * @author:[Dean]
     * @update:[2015-2-10] [更改人姓名][变更描述]
     */
    public static void broadcast(final String callBackMethod, final String msg)
    {
        for (final Map.Entry<Object, WebSocketBean> entry : connections.entrySet())
        {
            synchronized (entry.getKey())
            {
                if (entry.getValue().getCallBackMethods().contains(SysConstants.SUB_FAME_JS_PREFIX + callBackMethod))
                {
                    pushMessage(entry.getValue(), SysConstants.SUB_FAME_JS_PREFIX + callBackMethod, msg);
                }
            }
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[发送文本]
     * </p>
     * 
     * @param msg
     * @author:[xuqinghan]
     * @update:[2015-2-10] [更改人姓名][变更描述]
     */
    @SuppressWarnings("unchecked")
    public static void sendText(final String msg)
    {
        // msg to assets
        ZMQMonitorData[] zmqDatas = null;
        try
        {
            zmqDatas = new ObjectMapper().readValue(msg, ZMQMonitorData[].class);
        }
        catch (JsonParseException e)
        {
            LogUtil.error(log, e.getMessage());
        }
        catch (JsonMappingException e)
        {
            LogUtil.error(log, e.getMessage());
        }
        catch (IOException e)
        {
            LogUtil.error(log, e.getMessage());
        }
        for (final Map.Entry<Object, WebSocketBean> entry : connections.entrySet())
        {
            synchronized (entry.getKey())
            {
                List<Predicate> predicates = new ArrayList<>();
                Map<String, Set<String>> topicFQN = new HashMap<>();
                for (TopicVO topicVO : entry.getValue().getTopics())
                {
                    List<ResourceVO> resources = topicVO.getResources();
                    for (ResourceVO resource : resources)
                    {
                        String fqn = resource.getFqn().toUpperCase();
                        if (topicFQN.containsKey(fqn))
                        {
                            topicFQN.get(fqn).add(topicVO.getCallBackMethod());
                        }
                        else
                        {
                            Set<String> callbackMethod = new HashSet<>();
                            callbackMethod.add(topicVO.getCallBackMethod());
                            topicFQN.put(fqn, callbackMethod);
                        }
                        // TopicFQN.add(resource.getFqn());
                        predicates.add(new BeanPropertyValueEqualsPredicate("fqn", fqn));
                    }
                }
                Predicate predicate = PredicateUtils.anyPredicate(predicates);
                
                // Predicate predicate2 =new PredicateImpl("grade","A");
                Collection<ZMQMonitorData> zlists = CollectionUtils.select(ArrayUtils.toList(zmqDatas), predicate);
                if (!CollectionUtils.isEmpty(zlists))
                {
                    for (ZMQMonitorData zmqMonitorData : zlists)
                    {
                        String fqn = zmqMonitorData.getFqn();
                        Set<String> callbackMethods = topicFQN.get(fqn);
                        List<ZMQMonitorData> zResult = new ArrayList<>(1);
                        zResult.add(zmqMonitorData);
                        String result = "";
                        try
                        {
                            result = new ObjectMapper().writeValueAsString(zResult);
                        }
                        catch (JsonParseException e)
                        {
                            LogUtil.error(log, e.getMessage());
                        }
                        catch (JsonMappingException e)
                        {
                            LogUtil.error(log, e.getMessage());
                        }
                        catch (IOException e)
                        {
                            LogUtil.error(log, e.getMessage());
                        }
                        for (String callbackMethod : callbackMethods)
                        {
                            pushMessage(entry.getValue(), callbackMethod, result);
                            // pushMessage(entry.getValue(), "console.info", callbackMethod);
                        }
                    }
                    
                }
                
            }
        }
    }
    /**
     * 
     * <p>
     * Discription:[发送文本]
     * </p>
     * 
     * @param topic
     * @param msg
     * @author:[xuqinghan]
     * @update:[2015-2-10] [更改人姓名][变更描述]
     */
    public static void broadcastByTopic(final String topic,final String msg)
    {
        // msg to assets
        for (final Map.Entry<Object, WebSocketBean> entry : connections.entrySet())
        {
            synchronized (entry.getKey())
            {
                for (TopicVO topicVO : entry.getValue().getTopics())
                {
                    if(topic.equals(topicVO.getName())){
                        pushMessage(entry.getValue(), topicVO.getCallBackMethod(), msg);
                    }
                }
            }
        }
    }
    /**
     * 
     * <p>
     * Discription:[组装JS方法，调用推送]
     * </p>
     * 
     * @param method
     * @param message
     * @return
     * @author:[Dean]
     * @update:[2015-2-10] [更改人姓名][变更描述]
     */
    public static String invokeByJs(final String method, final String message)
    {
        return new StringBuffer(method).append("('").append(message).append("')").toString();
    }
    
    /**
     * 
     * <p>
     * Discription:[根据链接，和推送方法来推送到前台]
     * </p>
     * 
     * @param socketBean
     * @param callBackMethod
     * @param msg
     * @author:[Dean]
     * @update:[2015-2-10] [更改人姓名][变更描述]
     */
    private static void pushMessage(final WebSocketBean socketBean, final String callBackMethod, final String msg)
    {
        try
        {
            socketBean.getSession().getBasicRemote().sendText(WebSocketUtil.invokeByJs(callBackMethod, msg));
            
        }
        catch (final Exception e)
        {
            LogUtil.error(log, "Chat Error: Failed to send message to client", e);
            connections.remove(socketBean.getSession().getId());
            try
            {
                socketBean.getSession().close();
            }
            catch (final IOException e1)
            {
                LogUtil.error(log, e1.getMessage());
            }
            final String message = String.format("* %s %s", callBackMethod, "has been disconnected.");
            LogUtil.error(log, message);
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[通过Session Id来推送]
     * </p>
     * 
     * @param callBackMethod
     * @param sessionId
     * @author:[Dean]
     * @update:[2015-8-12] [更改人姓名][变更描述]
     */
    public static void pushWithSessionId(final String callBackMethod, final String sessionId)
    {
        if (null != connections.get(sessionId))
        {
            pushMessage(connections.get(sessionId), callBackMethod, sessionId);
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[推送数据]
     * </p>
     * 
     * @param callBackMethod
     * @param msg
     * @author:[Dean]
     * @update:[2015-8-12] [更改人姓名][变更描述]
     */
    public static void sysBroadcast(final String callBackMethod, final String msg)
    {
        for (final Map.Entry<Object, WebSocketBean> entry : connections.entrySet())
        {
            
            if (entry.getValue().getCallBackMethods().contains(callBackMethod))
            {
                synchronized (entry.getKey())
                {
                    pushMessage(entry.getValue(), callBackMethod, msg);
                }
            }
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[添加Topic]
     * </p>
     * 
     * @param topicName
     * @param assets
     * @author:[xuqinghan]
     * @update:[2015-8-12] [更改人姓名][变更描述]
     */
    public static void addTopic(String topicName, ResourceVO[] assets)
    {
        for (final Map.Entry<Object, WebSocketBean> entry : connections.entrySet())
        {
            entry.getValue().addTopic(topicName, assets);
        }
    }
    
}
