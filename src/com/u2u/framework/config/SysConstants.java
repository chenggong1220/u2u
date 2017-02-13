package com.u2u.framework.config;

/**
 * 
 * @ClassName: SysConstants <br>
 * @Description: 系统级别常量类 <br>
 * @date 2015-3-2 上午11:25:00 <br>
 * 
 * @author Freud
 */
public class SysConstants
{
    
    public static final long SECOND = 1000;
    
    public static final long MINUTE = 60 * SECOND;
    
    public static final long HOUR = 60 * MINUTE;
    
    public static final long DAY = 24 * HOUR;
    
    public static final long WEEK = 7 * DAY;
    
    public static final String SUB_SYSTEM_KEY = "SUB_SYSTEM_KEY";
    
    public static final String SUB_FAME_JS_PREFIX =
        "$(window.parent.document).contents().find('#ibms_main_content_02')[0].contentWindow.";
    
}