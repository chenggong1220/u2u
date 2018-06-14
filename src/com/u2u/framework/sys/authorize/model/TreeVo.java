package com.u2u.framework.sys.authorize.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeVo
{
    private String id;
    
    private String text;
    
    private String state = "closed";
    
    private String checked;
    
    private String iconCls;
    
    private String target;
    
    private Map<Object, Object> attributes = new HashMap<Object, Object>();
    
    private List<TreeVo> children = new ArrayList<>();
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return String iconCls.
     */
    public String getIconCls()
    {
        return iconCls;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param iconCls The iconCls to set.
     */
    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return String target.
     */
    public String getTarget()
    {
        return target;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param target The target to set.
     */
    public void setTarget(String target)
    {
        this.target = target;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public List<TreeVo> getChildren()
    {
        return children;
    }
    
    public void setChildren(List<TreeVo> children)
    {
        this.children = children;
    }
    
    public String getState()
    {
        return state;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }
    
    public String getChecked()
    {
        return checked;
    }
    
    public void setChecked(String checked)
    {
        this.checked = checked;
    }
    
    public Map<Object, Object> getAttributes()
    {
        return attributes;
    }
    
    public void setAttributes(Map<Object, Object> attributes)
    {
        this.attributes = attributes;
    }
}
