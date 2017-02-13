package com.u2u.framework.webscoket;

import java.util.ArrayList;
import java.util.List;

public class TopicVO
{
    private String name;
    
    private String callBackMethod;
    
    private List<ResourceVO> resources = new ArrayList<>();
    
    public TopicVO(String name)
    {
        this.name = name;
    }
    
    public TopicVO(String name, String callBackMethod)
    {
        this.name = name;
        
        this.callBackMethod = callBackMethod;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    
    public String getCallBackMethod()
    {
        return callBackMethod;
    }
    
    public void setCallBackMethod(String callBackMethod)
    {
        this.callBackMethod = callBackMethod;
    }

    public List<ResourceVO> getResources()
    {
        return resources;
    }

    public void setResources(List<ResourceVO> resources)
    {
        this.resources = resources;
    }

}
