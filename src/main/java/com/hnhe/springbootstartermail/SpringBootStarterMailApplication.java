package com.hnhe.springbootstartermail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在测试中遇到的一些问题：Spring Aop方式拦截自定义注解未生效问题
 * 需要在启动类上加上包扫描 否则不生效
 */
@SpringBootApplication(scanBasePackages = "com.hnhe.springbootstartermail")
public class SpringBootStarterMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterMailApplication.class, args);
    }

}
