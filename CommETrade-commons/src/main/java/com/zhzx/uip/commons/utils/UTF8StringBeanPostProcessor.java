package com.zhzx.uip.commons.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.util.List;


public class UTF8StringBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof StringHttpMessageConverter) {
			List<MediaType> types = Lists.asList(UTF8StringHttpMessageConverter.utf8TextPlain
                    , new MediaType[]{UTF8StringHttpMessageConverter.utf8TextHtml});
			((StringHttpMessageConverter) bean).setSupportedMediaTypes(types);
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
