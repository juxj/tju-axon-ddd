package com.example.demo.configuration;


import com.example.demo.validator.UserNameValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Slf4j
@Component
public class AxonCommandBeanPostProcessor implements BeanPostProcessor {

    private ConfigurableListableBeanFactory configurableBeanFactory;

    @Autowired
    public AxonCommandBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        this.configurableBeanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return this.scanDataAccessAnnotation(bean, beanName);
    }

    /**
     * 扫描聚合内各方法的注解，符合定义的项目，
     * @param bean .
     * @param beanName .
     * @return .
     */
    private Object scanDataAccessAnnotation(Object bean, String beanName) {
        // log.error(beanName);
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            UserNameValidator annotation = declaredField.getAnnotation(UserNameValidator.class);
            if (null != annotation) {
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, annotation);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        scanDataAccessAnnotation(bean, beanName);
        return bean;
    }

}