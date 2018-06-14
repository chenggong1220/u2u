package com.u2u.framework.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: ObjectUtils <br>
 * @Description: Object utils <br>
 * @date 2015-1-15 上午11:14:29 <br>
 * 
 * @author Dean
 */
public class ObjectUtils
{
    
    /**
     * <p>
     * Discription:[Whether Map,Collection,String,Array is not empty.]
     * </p>
     * 
     * @param o
     * @return
     * @author:[Dean]
     */
    @SuppressWarnings("all")
    public static boolean isEmpty(final Object o)
    {
        if (o == null)
        {
            return true;
        }
        
        if (o instanceof String)
        {
            if (((String)o).length() == 0)
            {
                return true;
            }
        }
        else if (o instanceof Collection)
        {
            if (((Collection)o).isEmpty())
            {
                return true;
            }
        }
        else if (o.getClass().isArray())
        {
            if (Array.getLength(o) == 0)
            {
                return true;
            }
        }
        else if (o instanceof Map)
        {
            if (((Map)o).isEmpty())
            {
                return true;
            }
        }
        else
        {
            return false;
        }
        
        return false;
    }
    
    /**
     * <p>
     * Discription:[Whether Map,Collection,String,Array is empty.]
     * </p>
     * 
     * @param c Object
     * @return boolean
     * @throws IllegalArgumentException
     * @author:[Dean]
     */
    public static boolean isNotEmpty(final Object c)
        throws IllegalArgumentException
    {
        return !isEmpty(c);
    }
    
    /**
     * <p>
     * Discription:[if object or string will return true]
     * </p>
     * 
     * @param o Object
     * @return boolean
     * @author:[Dean]
     */
    public static boolean isNullOrEmptyString(final Object o)
    {
        if (o == null)
        {
            return true;
        }
        if (o instanceof String)
        {
            final String str = (String)o;
            if (str.length() == 0)
            {
                return true;
            }
        }
        return false;
    }
    
}
