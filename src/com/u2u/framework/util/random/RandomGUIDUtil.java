package com.u2u.framework.util.random;

/**
 * 
 * @ClassName: RandomGUIDUtil <br>
 * @Description: 产生唯一的随机字符串 <br>
 * @date 2015-3-2 下午2:31:40 <br>
 * 
 * @author Dean
 */
public class RandomGUIDUtil
{
    /**
     * 产生唯一的随机字符串
     * 
     * @return
     */
    public static String generateKey()
    {
        return new RandomGUID(true).toString();
    }
}