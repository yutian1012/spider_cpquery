package org.ipph.spider.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.ipph.spider.enumeration.LogModelEnum;

/**
 * 定义日志模块的注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})  
@Retention(RetentionPolicy.RUNTIME) 
public @interface PageProcessorLog {
	
	LogModelEnum model() default LogModelEnum.NULL;
}
