package com.u2u.framework.util;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: MessageUtil <br>
 * @Description: Format String. <br>
 * @date 2015-1-15 上午11:00:51 <br>
 * 
 * @author Dean
 */
public class MessageUtil
{
    private static int BEGIN = 45217;
    
    private static int END = 63486;
    
    private static char[] chartable = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪',
        '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝',};
    
    private static int[] table = new int[27];
    
    private static char[] initialtable = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o',
        'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z',};
    
    // 初始化
    static
    {
        for (int i = 0; i < 26; i++)
        {
            table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
        }
        table[26] = END;// 区间表结尾
    }
    
    private static char Char2Initial(final char ch)
    {
        if (ch >= 'a' && ch <= 'z')
        {
            return (char)(ch - 'a' + 'A');
        }
        if (ch >= 'A' && ch <= 'Z')
        {
            return ch;
        }
        final int gb = gbValue(ch);// 汉字转换首字母
        if (gb < BEGIN || gb > END)
        {
            return ch;
        }
        int i;
        for (i = 0; i < 26; i++)
        {
            if (gb >= table[i] && gb < table[i + 1])
            {
                break;
            }
        }
        
        if (gb == END)
        {
            i = 25;
        }
        return initialtable[i];
    }
    
    /**
     * <p>
     * Discription:[获取字符串拼音首字母]
     * </p>
     * 
     * @param SourceStr String
     * @return String
     * @author:[Dean]
     */
    public static String cn2py(final String SourceStr)
    {
        String Result = "";
        final int StrLength = SourceStr.length();
        int i;
        try
        {
            for (i = 0; i < StrLength; i++)
            {
                Result += Char2Initial(SourceStr.charAt(i));
            }
        }
        catch (final Exception e)
        {
            Result = "";
        }
        return Result;
    }
    
    /**
     * <p>
     * Discription:[format string with parameters]
     * </p>
     * 
     * @param pattern String
     * @param args ...
     * @return String
     * @author:[Dean]
     */
    public static String formmatString(final String pattern, final Object... args)
    {
        if (StringUtils.isEmpty(pattern))
        {
            throw new java.lang.IllegalArgumentException();
        }
        return MessageFormat.format(pattern, args);
    }
    
    private static int gbValue(final char ch)
    {
        String str = new String();
        str += ch;
        try
        {
            final byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2)
            {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        }
        catch (final Exception e)
        {
            return 0;
        }
    }
    
}
