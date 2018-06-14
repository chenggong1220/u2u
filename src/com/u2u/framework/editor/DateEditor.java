package com.u2u.framework.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.u2u.framework.util.DateUtil;

/**
 * 
 * @ClassName: DateEditor <br>
 * @Description: Date与String类型转换器 <br>
 * @date 2015-3-2 上午11:33:42 <br>
 * 
 * @author Freud
 */
public class DateEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String text)
        throws IllegalArgumentException
    {
        
        if (!StringUtils.hasText(text))
        {
            setValue(null);
        }
        else
        {
            setValue(DateUtil.string2Date(text, "yyyy-MM-dd"));
        }
    }
    
    @Override
    public String getAsText()
    {
        
        return getValue().toString();
    }
}
