package com.u2u.framework.sys.log.beans;

/**
 * @ClassName: LogType <br>
 * @Description: 日志类型 <br>
 * @date 2015年1月16日 上午11:24:55 <br>
 * 
 * @author Freud
 */
public class LogType
{
    
    /** The id. */
    private int id;
    
    /** 操作用户 */
    private String username;
    
    /** 操作对象 */
    private String operateObject;
    
    /** 操作描述 */
    private String operateDesc;
    
    /** 操作时间 */
    private String operateTime;
    
    /** 操作人IP */
    private String operateIp;
    
    /**
     * <p>
     * Discription:[Get id]
     * </p>
     * 
     * @return int id.
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * <p>
     * Discription:[Set id]
     * </p>
     * 
     * @param id The id to set.
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * <p>
     * Discription:[Get username]
     * </p>
     * 
     * @return String username.
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * <p>
     * Discription:[Set username]
     * </p>
     * 
     * @param username The username to set.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * <p>
     * Discription:[Get operateObject]
     * </p>
     * 
     * @return String operateObject.
     */
    public String getOperateObject()
    {
        return operateObject;
    }
    
    /**
     * <p>
     * Discription:[Set operateObject]
     * </p>
     * 
     * @param operateObject The operateObject to set.
     */
    public void setOperateObject(String operateObject)
    {
        this.operateObject = operateObject;
    }
    
    /**
     * <p>
     * Discription:[Get operateDesc]
     * </p>
     * 
     * @return String operateDesc.
     */
    public String getOperateDesc()
    {
        return operateDesc;
    }
    
    /**
     * <p>
     * Discription:[Set operateDesc]
     * </p>
     * 
     * @param operateDesc The operateDesc to set.
     */
    public void setOperateDesc(String operateDesc)
    {
        this.operateDesc = operateDesc;
    }
    
    /**
     * <p>
     * Discription:[Get operateTime]
     * </p>
     * 
     * @return String operateTime.
     */
    public String getOperateTime()
    {
        return operateTime;
    }
    
    /**
     * <p>
     * Discription:[Set operateTime]
     * </p>
     * 
     * @param operateTime The operateTime to set.
     */
    public void setOperateTime(String operateTime)
    {
        this.operateTime = operateTime;
    }
    
    /**
     * <p>
     * Discription:[Get operateIp]
     * </p>
     * 
     * @return String operateIp.
     */
    public String getOperateIp()
    {
        return operateIp;
    }
    
    /**
     * <p>
     * Discription:[Set operateIp]
     * </p>
     * 
     * @param operateIp The operateIp to set.
     */
    public void setOperateIp(String operateIp)
    {
        this.operateIp = operateIp;
    }
    
}
