package io.github.nirvanafire.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>@author: NirvanaFire(nirvanafire@gmail.com)
 * <p>@description: AppConfig
 * <p>@since: v1.1
 **/
@Configuration
@ComponentScan("io.github.nirvanafire")
public class AppConfig implements WebMvcConfigurer {

    /**
     * 利用commons-fileupload实现文件上传功能
     */
    /*@Bean("multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }*/

    /**
     * 利用Servlet3.0实现文件上传供能
     */
    @Bean("multipartResolver")
    public StandardServletMultipartResolver standardServletMultipartResolver() {
        StandardServletMultipartResolver standardServletMultipartResolver = new StandardServletMultipartResolver();
        return standardServletMultipartResolver;
    }
}
