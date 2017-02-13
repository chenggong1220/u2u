package com.u2u.common.component.timer;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @ClassName: AutoWiringSpringBeanJobFactory <br>
 * @Description: TODO <br>
 * @date 2015-6-15 下午5:54:54 <br>
 * 
 * @author Freud
 */
public class AutoWiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware
{
    private transient AutowireCapableBeanFactory beanFactory;
    
    public void setApplicationContext(final ApplicationContext context)
    {
        beanFactory = context.getAutowireCapableBeanFactory();
    }
    
    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle)
        throws Exception
    {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}