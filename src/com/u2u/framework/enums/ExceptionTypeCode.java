package com.u2u.framework.enums;

/**
 * @ClassName: ExceptionTypeCode <br>
 * @Description: System error code enum. <br>
 * @date 2015年1月22日 下午4:04:40 <br>
 * 
 * @author dean
 */
public enum ExceptionTypeCode implements BaseEnum<String>
{
    
    /**
     * 系统错误及编码
     */
    CD_SYS("系统错误", "1001000"),
    
    /**
     * 业务错误及编码
     */
    CD_BUSINESS("业务错误", "1002000"),
    
    /**
     * 数据库错误及编码
     */
    CD_DB("数据库错误", "1003000"),
    
    /**
     * 验证或权限异常及编码
     */
    CD_AUTHORIZATION("验证或权限异常", "1004000"),
    
    /**
     * CSS server异常及编码
     */
    CD_CSS("CSS server异常", "1005000"),
    
    /**
     * 定时器错误及编码
     */
    CD_TIMER("定时器错误", "1006000"),
    
    /**
     * 文件操作出错及编码
     */
    CD_FILE("文件操作出错", "1007000"),
    
    /**
     * 线程出错及编码
     */
    CD_THREAD("线程出错", "1008000"),
    
    /**
     * 短信猫出错及编码
     */
    CD_SMS("短信猫出错", "1009000"),
    
    /**
     * 邮件操作出错及编码
     */
    CD_EMAIL("邮件操作出错", "1010000");
    
    private String desc;
    
    private String code;
    
    private ExceptionTypeCode(String desc, String code)
    {
        this.desc = desc;
        this.code = code;
    }
    
    public String getCode()
    {
        return this.code;
    }
    
    public String getDesc()
    {
        return this.desc;
    }
    
}
