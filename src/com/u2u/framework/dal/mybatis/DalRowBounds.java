package com.u2u.framework.dal.mybatis;

import org.apache.ibatis.session.RowBounds;

/**
 * 
 * @ClassName: DalRowBounds <br>
 * @Description: 方言 <br>
 * @date 2015-3-2 上午11:30:16 <br>
 * 
 * @author Freud
 */
public class DalRowBounds extends RowBounds
{
    
    private int extOffset;
    
    private int extLimit;
    
    private int total;
    
    public DalRowBounds(int offset, int limit)
    {
        this.extOffset = offset;
        this.extLimit = limit;
    }
    
    public int getExtOffset()
    {
        return extOffset;
    }
    
    public int getExtLimit()
    {
        return extLimit;
    }
    
    public int getTotal()
    {
        return total;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
    
}
