package com.u2u.framework.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @ClassName: SpringContextHolder <br>
 * @Description: Spring context 的支持类 <br>
 * @date 2015-3-2 下午1:44:21 <br>
 * 
 * @author Freud
 */
public class SpringContextHolder implements ApplicationContextAware
{
    
    private static ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        SpringContextHolder.applicationContext = applicationContext;
    }
    
    /**
     * 得到Spring 上下文环境
     * 
     * @return
     */
    public static ApplicationContext getApplicationContext()
    {
        checkApplicationContext();
        return applicationContext;
    }
    
    /**
     * 通过Spring Bean name 得到Bean
     * 
     * @param name bean 上下文定义名称
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name)
    {
        checkApplicationContext();
        return (T)applicationContext.getBean(name);
    }
    
    /**
     * 
     * <p>
     * Discription:[通过类型得到Bean]
     * </p>
     * 
     * @param clazz
     * @return
     * @author:[Freud]
     * @update:[2015-8-12] [更改人姓名][变更描述]
     */
    public static <T> T getBean(Class<T> clazz)
    {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }
    
    /**
     * 
     * <p>
     * Discription:[检查ApplicationContext有没有注入]
     * </p>
     * 
     * @author:[Freud]
     * @update:[2015-3-12] [更改人姓名][变更描述]
     */
    private static void checkApplicationContext()
    {
        if (applicationContext == null)
        {
            throw new IllegalStateException("applicaitonContext未注入,请在application-context.xml中定义SpringContextHolder");
        }
    }
    
}
