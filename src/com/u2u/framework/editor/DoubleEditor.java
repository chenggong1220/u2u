package com.u2u.framework.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * 
 * @ClassName: DoubleEditor <br>
 * @Description: Double与String类型转换器 <br>
 * @date 2015-3-2 上午11:34:05 <br>
 * 
 * @author Freud
 */
public class DoubleEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String text)
        throws IllegalArgumentException
    {
        if (text == null || text.equals(""))
            text = "0";
        if (!StringUtils.hasText(text))
        {
            setValue(null);
        }
        else
        {
            setValue(Double.parseDouble(text));
        }
    }
    
    @Override
    public String getAsText()
    {
        
        return getValue().toString();
    }
}
