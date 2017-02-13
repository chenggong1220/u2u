package com.u2u.framework.dal.mybatis.dialect;

/**
 * 
 * @ClassName: MySQLDialect <br>
 * @Description: MySql的分页方言实现 <br>
 * @date 2015-3-2 上午11:25:50 <br>
 * 
 * @author Freud
 */
public class MySQLDialect extends Dialect
{
    
    public boolean supportsLimitOffset()
    {
        return true;
    }
    
    public boolean supportsLimit()
    {
        return true;
    }
    
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
    {
        if (offset > 0 && limit >= 0)
        {
            return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
        }
        else
        {
            return sql + " limit " + limitPlaceholder;
        }
    }
    
}
