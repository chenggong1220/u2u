package com.u2u.framework.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * 
 * @ClassName: LongEditor <br>
 * @Description: Long与String类型转换器 <br>
 * @date 2015-3-2 下午2:21:14 <br>
 * 
 * @author Freud
 */
public class LongEditor extends PropertyEditorSupport
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
            setValue(Long.parseLong(text));
        }
    }
    
    @Override
    public String getAsText()
    {
        
        return getValue().toString();
    }
}
