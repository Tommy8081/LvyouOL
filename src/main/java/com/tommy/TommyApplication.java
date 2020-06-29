package com.tommy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * SpringBoot启动类
 */
@SpringBootApplication
@MapperScan(value = "com.tommy.mapper")
public class TommyApplication {
    public static void main(String[] args) {

        SpringApplication.run(TommyApplication.class, args);
    }
    /**
     * 配置JSP视图解析器
     * 如果没有配置视图解析器。Spring会使用BeanNameViewResolver，通过查找ID与逻辑视图名称匹配且实现了View接口的beans
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        /** 设置视图路径的前缀 */
        resolver.setPrefix("/");
        /** 设置视图路径的后缀 */
        resolver.setSuffix(".html");
        return resolver;
    }
}
