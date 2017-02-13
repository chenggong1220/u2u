package com.u2u.framework.adapter.interceptor;

import java.text.MessageFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.u2u.framework.config.SysConstants;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.sys.log.beans.LogType;
import com.u2u.framework.sys.log.service.LoggerService;
import com.u2u.framework.util.DateUtil;
import com.u2u.framework.util.LogUtil;

/**
 * @ClassName: DBMethodInterceptor <br>
 * @Description: 日志切面记录拦截器 <br>
 * @date 2015年1月15日 下午2:22:09 <br>
 * 
 * @author Freud
 */
@Aspect
public class DBLoggerMethodInterceptor
{
    
    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(DBLoggerMethodInterceptor.class);
    
    /**
     * LoggerService
     */
    private LoggerService loggerService;
    
    /**
     * Request
     */
    private HttpServletRequest request;
    
    /**
     * @param loggerService the loggerService to set
     */
    public void setLoggerService(LoggerService loggerService)
    {
        this.loggerService = loggerService;
    }
    
    /**
     * @param request the request to set
     */
    @Autowired
    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    /**
     * 
     * <p>
     * Discription:[insert和add的切面定义]
     * </p>
     * 
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    @Pointcut("execution(public * com.u2u.ibms..controller.*.insert*(..))||execution(* com.u2u.ibms..controller.*.add*(..))")
    public void insert()
    {
    }
    
    /**
     * 
     * <p>
     * Discription:[update和edit的切面定义]
     * </p>
     * 
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    @Pointcut("execution(* com.u2u.ibms..controller.*.update*(..))||execution(* com.u2u.ibms..controller.*.edit*(..))")
    public void udpate()
    {
    }
    
    /**
     * 
     * <p>
     * Discription:[remove和delete的切面定义]
     * </p>
     * 
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    @Pointcut("execution(* com.u2u.ibms..controller.*.remove*(..))||execution(* com.u2u.ibms..controller.*.delete*(..))")
    public void remove()
    {
    }
    
    /**
     * 
     * <p>
     * Discription:[insert()切面下的日志入库操作]
     * </p>
     * 
     * @param jp
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    @AfterReturning("insert()")
    public void doInsert(JoinPoint jp)
    {
        String target = request.getSession().getAttribute(SysConstants.SUB_SYSTEM_KEY).toString();
        String ip = request.getRemoteAddr();
        this.logIntoDB(target, ip, "新增");
    }
    
    /**
     * 
     * <p>
     * Discription:[update()切面下的日志入库操作]
     * </p>
     * 
     * @param jp
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    @AfterReturning("udpate()")
    public void doUpdate(JoinPoint jp)
    {
        
        String target = request.getSession().getAttribute(SysConstants.SUB_SYSTEM_KEY).toString();
        String ip = request.getRemoteAddr();
        this.logIntoDB(target, ip, "更新");
        
    }
    
    /**
     * 
     * <p>
     * Discription:[remove()切面下的日志入库操作]
     * </p>
     * 
     * @param jp
     * @author:[Freud]
     * @update:[2015-3-2] [更改人姓名][变更描述]
     */
    @AfterReturning("remove()")
    public void doRemove(JoinPoint jp)
    {
        
        String target = request.getSession().getAttribute(SysConstants.SUB_SYSTEM_KEY).toString();
        String ip = request.getRemoteAddr();
        this.logIntoDB(target, ip, "删除");
        
    }
    
    /**
     * 执行日志入库操作
     * 
     * @param targetObj
     * @param arguments
     * @param ip
     * @param operateType
     */
    public void logIntoDB(String targetObj, String ip, String operateType)
    {
        
        String description = MessageFormat.format("[{0}]操作", operateType);
        
        LogType logType = new LogType();
        
        String username = SecurityContextUtil.getUserName();
        String currentDate = DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD);
        
        logType.setUsername(username);
        logType.setOperateObject(targetObj);
        logType.setOperateDesc(description);
        logType.setOperateTime(currentDate);
        logType.setOperateIp(ip);
        
        loggerService.insertLog(logType);
        
        /**
         * 0-用户名 1-时间 2-对象 3-操作类型 4-参数 5-IP
         */
        String operateLogDescription = "用户[{0}]在[{1}]时间对[{2}]进行了[{3}]操作,用户IP[{4}]";
        LogUtil.info(log, operateLogDescription, username, currentDate, targetObj, operateType, ip);
    }
    
}
