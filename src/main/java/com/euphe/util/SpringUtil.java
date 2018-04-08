package com.euphe.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring bean 获取类
 *
 *
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext ac = null;

    @Override
    @SuppressWarnings("static-access")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac=applicationContext;
    }

    public synchronized static Object getBean(String name){
        return ac.getBean(name);
    }
}
