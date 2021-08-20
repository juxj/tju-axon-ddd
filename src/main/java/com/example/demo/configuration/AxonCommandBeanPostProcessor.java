package com.example.demo.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


@Component
@Slf4j
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

    private Object scanDataAccessAnnotation(Object bean, String beanName) {
        // log.error(beanName);
        // Field[] declaredFields = bean.getClass().getDeclaredFields();
        // for (Field declaredField : declaredFields) {
        //     TestValidator annotation = declaredField.getAnnotation(TestValidator.class);
        //     if (null == annotation) {
        //         continue;
        //     }
        //     declaredField.setAccessible(true);
        //     try {
        //         declaredField.set(bean, annotation);
        //     } catch (IllegalAccessException e) {
        //         e.printStackTrace();
        //     }
        // }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

}