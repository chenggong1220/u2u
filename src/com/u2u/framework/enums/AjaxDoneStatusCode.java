package com.u2u.framework.enums;

/**
 * @ClassName: AjaxDoneStatusCode <br>
 * @Description: return for web page. <br>
 * @date 2015年1月22日 下午5:39:25 <br>
 * 
 * @author Freud
 */
public enum AjaxDoneStatusCode implements BaseEnum<String>
{
    
    /**
     * 操作成功返回码和操作描述
     */
    CD_SUCCESS("200", "操作成功!"),
    /**
     * 操作失败返回码和操作描述
     */
    CD_FAILED("300", "操作失败!");
    
    /**
     * 返回码
     */
    private String code;
    
    /**
     * 返回描述
     */
    private String desc;
    
    private AjaxDoneStatusCode(String code, String desc)
    {
        this.desc = desc;
        this.code = code;
    }
    
    @Override
    public String getCode()
    {
        return this.code;
    }
    
    @Override
    public String getDesc()
    {
        return this.desc;
    }
}
