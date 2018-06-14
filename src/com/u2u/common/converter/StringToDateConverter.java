package com.u2u.common.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @ClassName: StringToDateConverter <br>
 * @Description: Spring的类型转换器：从String转换到Date类型 <br>
 * @date 2015-2-13 下午5:03:48 <br>
 * 
 * @author Freud
 */
public class StringToDateConverter implements Converter<String, Date>
{
    
    /**
     * 
     * <p>
     * Discription:[转换方法]
     * </p>
     * 
     * @param source
     * @return
     * @author:[Freud]
     * @update:[2015-2-13] [更改人姓名][变更描述]
     */
    public Date convert(String source)
    {
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = dateTimeFormat.parse(source);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
}
