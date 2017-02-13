package com.u2u.framework.webscoket;


public class ResourceVO
{
    private String fqn;
    
    private String[] metric_list;
    
    public String getFqn()
    {
        return fqn;
    }
    
    public void setFqn(String fqn)
    {
        this.fqn = fqn;
    }
    
    public String[] getMetric_list()
    {
        return metric_list;
    }
    
    public void setMetric_list(String[] metric_list)
    {
        this.metric_list = metric_list;
    }
    
}
