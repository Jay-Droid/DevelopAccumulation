package com.jay.develop.java.dynamic_proxy.practice;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 注解参数,标示参数注解
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-02 18:19
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface RouterParam {
    String value() default "";
}
