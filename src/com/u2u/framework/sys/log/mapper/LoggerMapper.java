package com.u2u.framework.sys.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.framework.sys.log.beans.LogType;

/**
 * @ClassName: LoggerMapper <br>
 * @Description: 日志管理数据库操作接口 <br>
 * @date 2015年1月14日 上午11:30:57 <br>
 * 
 * @author Freud
 */
public interface LoggerMapper
{
    /**
     * 
     * <p>
     * Discription:[获取所有的日志记录]
     * </p>
     * 
     * @return
     * @author:[Freud]
     * @update:[2015-1-14] [更改人姓名][变更描述]
     */
    List<LogType> getLogs();
    
    /**
     * 
     * <p>
     * Discription:[通过条件组合查询日志记录]
     * </p>
     * 
     * @param sort
     * @param order
     * @param username
     * @param startDate
     * @param endDate
     * @param rb
     * @return
     * @author:[Freud]
     * @update:[2015-8-12] [更改人姓名][变更描述]
     */
    List<LogType> getLogs(@Param("sort")
    String sort, @Param("order")
    String order, @Param("username")
    String username, @Param("startDate")
    String startDate, @Param("endDate")
    String endDate, RowBounds rb);
    
    /**
     * 
     * <p>
     * Discription:[获取日志记录的总数量]
     * </p>
     * 
     * @param username
     * @param startDate
     * @param endDate
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    int getLogsSize(@Param("username")
    String username, @Param("startDate")
    String startDate, @Param("endDate")
    String endDate);
    
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
    void insertLog(LogType logType);
    
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
    void updateLog(LogType logType);
    
    /**
     * 
     * <p>
     * Discription:[通过ID列表删除日志记录]
     * </p>
     * 
     * @param ids
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    void deleteLog(@Param("ids")
    int[] ids);
}
