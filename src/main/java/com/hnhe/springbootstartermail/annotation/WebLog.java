package com.hnhe.springbootstartermail.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)// 什么时候使用该注解 定义为运行时
@Target({ElementType.METHOD})// 注解作用在什么地方  定义作用在方法上
@Documented //注解是否将包含在javaDoc中
//注解名为weblog
public @interface WebLog {

    /**
     * 日志描述信息
     * @return
     */
    String description() default ""; //定义一个属性默认为空字符串

}
