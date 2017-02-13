package com.u2u.framework.dal.mybatis.dialect;

/**
 * 
 * @ClassName: PostgreSQLDialect <br>
 * @Description: PostgreSQL的分页方言实现 <br>
 * @date 2015-3-2 上午11:28:01 <br>
 * 
 * @author Freud
 */
public class PostgreSQLDialect extends Dialect
{
    
    public boolean supportsLimit()
    {
        return true;
    }
    
    public boolean supportsLimitOffset()
    {
        return true;
    }
    
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
    {
        return new StringBuffer(sql.length() + 20).append(sql)
            .append(offset > 0 ? " limit " + limitPlaceholder + " offset " + offsetPlaceholder : " limit "
                + limitPlaceholder)
            .toString();
    }
}
