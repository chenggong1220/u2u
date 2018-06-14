package com.u2u.common.component.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.u2u.framework.util.LogUtil;
import com.u2u.framework.util.ThreadUtils;

/**
 * @ClassName: ThreadPoolManager <br>
 * @Description: Thread Pool Manager <br>
 * @date 2015-1-12 下午05:27:18 <br>
 * 
 * @author dean
 */
public class ThreadPoolManager
{
    
    /** Log info. */
    private static Log log = LogFactory.getLog(ThreadPoolManager.class);
    
    /*** Default core size. */
    private static int maxsize = 20;
    
    /** default keep Alive Time. */
    private static long waitTime = 2 * 60 * 1000L; // 2min, waiting pool
    
    /** Log message formatter. */
    private static String strFormatter =
        "Make ThreadPool with coresize= %s,maxsize=%s,keepAliveTime=%s and create by %s";
    
    /**
     * <p>
     * Discription:[Create default thread pool with default WorkerThreadFactory.]
     * </p>
     * 
     * @param <T> ThreadPool used by.
     * @param clazz ThreadPool used by.
     * @return ThreadPoolExecutor
     * @author:[Dean]
     */
    public static <T> ThreadPoolExecutor defaultThreadPool(final Class<T> clazz)
    {
        LogUtil.info(log, strFormatter, maxsize, maxsize, waitTime, clazz.getName());
        return new ThreadPoolExecutor(maxsize, maxsize, waitTime, TimeUnit.MILLISECONDS,
            new SynchronousQueue<Runnable>(), ThreadUtils.instanceThreadFactory(clazz.getSimpleName()));
        
    }
    
    /**
     * 
     * <p>
     * Discription:[Create default thread pool with pointed WorkerThreadFactory.]
     * </p>
     * 
     * @param clazz
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @return
     * @author:[Dean]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public static <T> ThreadPoolExecutor defaultThreadPool(final Class<T> clazz, final int corePoolSize,
        final int maximumPoolSize, final long keepAliveTime)
    {
        
        LogUtil.info(log, strFormatter, corePoolSize, maximumPoolSize, keepAliveTime, clazz.getName());
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
            new SynchronousQueue<Runnable>(), ThreadUtils.instanceThreadFactory(clazz.getSimpleName()));
        
    }
    
    /**
     * 
     * <p>
     * Discription:[Create default cached thread pool with default WorkerThreadFactory.]
     * </p>
     * 
     * @param clazz
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public static <T> ExecutorService newCachedThreadPool(final Class<T> clazz)
    {
        LogUtil.info(log, strFormatter, 0L, Integer.MAX_VALUE, 60L, clazz.getName());
        return Executors.newCachedThreadPool();
    }
    
    /**
     * 
     * <p>
     * Discription:[Create default cached thread pool with pointed WorkerThreadFactory.]
     * </p>
     * 
     * @param clazz
     * @param threadFactory
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public static <T> ExecutorService newCachedThreadPool(final Class<T> clazz, final ThreadFactory threadFactory)
    {
        LogUtil.info(log, strFormatter, 0L, Integer.MAX_VALUE, 60L, clazz.getName());
        return Executors.newCachedThreadPool(threadFactory);
    }
    
    /**
     * 
     * <p>
     * Discription:[Create default fixed thread pool with default WorkerThreadFactory.]
     * </p>
     * 
     * @param clazz
     * @param nThreads
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public static <T> ExecutorService newFixedThreadPool(final Class<T> clazz, final int nThreads)
    {
        LogUtil.info(log, strFormatter, nThreads, nThreads, 0L, clazz.getName());
        return Executors.newFixedThreadPool(nThreads);
    }
    
    /**
     * 
     * <p>
     * Discription:[Create default fixed thread pool with pointed WorkerThreadFactory.]
     * </p>
     * 
     * @param clazz
     * @param nThreads
     * @param threadFactory
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public static <T> ExecutorService newFixedThreadPool(final Class<T> clazz, final int nThreads,
        final ThreadFactory threadFactory)
    {
        LogUtil.info(log, strFormatter, nThreads, nThreads, 0L, clazz.getName());
        return Executors.newFixedThreadPool(nThreads, threadFactory);
    }
    
    /**
     * 
     * <p>
     * Discription:[Create default single thread pool with default WorkerThreadFactory.]
     * </p>
     * 
     * @param clazz
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public static <T> ExecutorService newSingleThreadExecutor(final Class<T> clazz)
    {
        LogUtil.info(log, strFormatter, 1, 1, 0L, clazz.getName());
        return Executors.newSingleThreadExecutor();
    }
    
    /**
     * 
     * <p>
     * Discription:[Create default single thread pool with pointed WorkerThreadFactory.]
     * </p>
     * 
     * @param clazz
     * @param threadFactory
     * @return
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public static <T> ExecutorService newSingleThreadExecutor(final Class<T> clazz, final ThreadFactory threadFactory)
    {
        LogUtil.info(log, strFormatter, 1, 1, 0L, clazz.getName());
        return Executors.newSingleThreadExecutor(threadFactory);
    }
    
}
