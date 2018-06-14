package com.u2u.framework.cache;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.u2u.framework.spring.SpringContextHolder;

/**
 * @ClassName: CacheManagerHandler <br>
 * @Description: CacheManager tool <br>
 * @date 2015-1-16 下午03:49:38 <br>
 * 
 * @author Dean
 */
public class CacheManagerFactory
{
    private static CacheManager cacheManager;
    
    /** manuallycache 对应配置文件中缓存名字. */
    private static String cacheName = "manuallyCache";
    
    /**
     * 获取CacheManagerFactory实例
     * @return
     */
    private static CacheManager getInstance()
    {
        if (cacheManager == null)
        {
            cacheManager =SpringContextHolder.getBean("cacheManager");
        }
        return cacheManager;
    }
    
    /**
     * Discription:[create cache by name.]
     * 
     * @param name String
     * @return Cache
     * @author:[Dean]
     */
    public static Cache createCache(final String name)
    {
        final net.sf.ehcache.CacheManager c = net.sf.ehcache.CacheManager.getInstance();
        c.addCache(name);
        return getCache(name);
    }
    
    /**
     * Discription:[获取manuallycache]
     * 
     * @return Cache
     * @author:[Dean]
     */
    public static Cache getCache()
    {
        return getInstance().getCache(cacheName);
    }
    
    /**
     * Discription:[根据名字获取对应cache.]
     * 
     * @param name String
     * @return Cache
     * @author:[Dean]
     */
    public static Cache getCache(final String name)
    {
        return getInstance().getCache(name);
    }
    
    /**
     * Discription:[获取所有chache名字.]
     * 
     * @return Collection<String>
     * @author:[Dean]
     */
    public static Collection<String> getCacheNames()
    {
        return getInstance().getCacheNames();
    }
    
}
