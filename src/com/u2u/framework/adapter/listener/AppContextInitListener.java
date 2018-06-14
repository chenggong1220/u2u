package com.u2u.framework.adapter.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.u2u.framework.config.AppConfiguration;
import com.u2u.framework.util.LogUtil;

/**
 * @ClassName: AppContextInitListener <br>
 * @Description: load app config xml. <br>
 * @date 2015-1-14 下午01:43:14 <br>
 * 
 * @author Dean
 */
public class AppContextInitListener implements ServletContextListener, HttpSessionListener
{
    
    private static final Log log = LogFactory.getLog(AppContextInitListener.class);
    
    private static final String ETC_FILE = "etc_file";
    
    public AppContextInitListener()
    {
        
    }
    
    @Override
    public void contextDestroyed(final ServletContextEvent event)
    {
        
        AppConfiguration.getInstance().clear();
    }
    
    @Override
    public void contextInitialized(final ServletContextEvent event)
    {
        final ServletContext context = event.getServletContext();
        final String configFile = context.getInitParameter(ETC_FILE);
        final String configFileRealPath = this.getClass().getClassLoader().getResource("/").getPath()+configFile;
        try
        {
            AppConfiguration.init(configFileRealPath);
        }
        catch (final Exception e)
        {
            // event.getServletContext().log("ConfigurationException: ", e);
            LogUtil.error(log, "load app config file error!");
        }
        
    }
    
    @Override
    public void sessionCreated(final HttpSessionEvent event)
    {
    }
    
    @Override
    public void sessionDestroyed(final HttpSessionEvent event)
    {
        final HttpSession session = event.getSession();
        session.invalidate();
    }
    
}
