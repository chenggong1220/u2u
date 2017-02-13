package com.u2u.framework.sys.log.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.framework.sys.log.beans.LogType;

/**
 * @ClassName: LoggerService <br>
 * @Description: 日志服务接口的定义 <br>
 * @date 2015年1月16日 下午1:20:46 <br>
 * 
 * @author Freud
 */
public interface LoggerService
{
    
    /**
     * 
     * <p>
     * Discription:[通过条件获取日志记录]
     * </p>
     * 
     * @param sort
     * @param order
     * @param username
     * @param startDate
     * @param endDate
     * @param rb
     * @return
     * @throws ServiceBusinessException
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public List<LogType> getLogs(String sort, String order, String username, String startDate, String endDate,
        RowBounds rb)
        throws ServiceBusinessException;
    
    /**
     * 
     * <p>
     * Discription:[根据条件获取日志记录的总个数]
     * </p>
     * 
     * @param username
     * @param startDate
     * @param endDate
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public int getLogsSize(String username, String startDate, String endDate);
    
    /**
     * 
     * <p>
     * Discription:[插入日志记录]
     * </p>
     * 
     * @param logType
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public void insertLog(LogType logType);
    
    /**
     * 
     * <p>
     * Discription:[更新日志记录]
     * </p>
     * 
     * @param logType
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public void updateLog(LogType logType);
    
    /**
     * 
     * <p>
     * Discription:[通过日志记录ID列表删除日志记录]
     * </p>
     * 
     * @param indexs
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public void deleteLog(String[] indexs);
}
