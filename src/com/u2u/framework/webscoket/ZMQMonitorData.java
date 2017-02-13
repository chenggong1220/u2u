package com.u2u.framework.webscoket;

import java.io.Serializable;
import java.util.Map;

/**
 * 封装了某个资源单次采集的监控数据
 * 
 * @author WanWei
 * @date 2014-12-24 上午11:40:00
 */
public class ZMQMonitorData implements Serializable
{
    
    /**
     * <p>
     * Discription:[字段功能描述]
     * </p>
     */
    private static final long serialVersionUID = -5686072196335828442L;
    
    private String fqn;// 用来表示某个资源的唯一标识，不能由数据库自动生成，不能为空
    
    private String name;// 资源的名称，不能为空
    
    private String category;
    
    private String createDate;
    
    private String deviceType;// 资源类型，不能为空
    
    // 版本号，一般在资源为软件的场景下涉及，不能为空，默认值为 v1.0
    private String deviceVersion;
    
    // 与资源相关的指标的监控数据
    private Map<String, ZMQMetricData> metricDatas;
    
    public String getFqn()
    {
        return fqn;
    }
    
    public void setFqn(String fqn)
    {
        this.fqn = fqn;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDeviceType()
    {
        return deviceType;
    }
    
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }
    
    public String getDeviceVersion()
    {
        return deviceVersion;
    }
    
    public void setDeviceVersion(String deviceVersion)
    {
        this.deviceVersion = deviceVersion;
    }
    
    public Map<String, ZMQMetricData> getMetricDatas()
    {
        return metricDatas;
    }
    
    public void setMetricDatas(Map<String, ZMQMetricData> metricDatas)
    {
        this.metricDatas = metricDatas;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
}
