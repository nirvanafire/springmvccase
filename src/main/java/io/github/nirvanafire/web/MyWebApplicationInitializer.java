package io.github.nirvanafire.web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;

/**
 * <p>@author: NirvanaFire(nirvanafire@gmail.com)
 * <p>@description: MyWebApplicationInitializer
 * <p>@since: v1.1
 **/
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("---MyApplicationInitializer onStartup---");

        // 加载Spring Web应用配置
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext ();
        ac.register(AppConfig.class);
        ac.refresh();

        // 创建并注册DispatcherServlet(前端控制器)
        DispatcherServlet ds = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatchServlet", ds);
        registration.setLoadOnStartup(1);
        registration.addMapping("*.do");

        System.out.println("---tmpdir: "  + System.getProperty("java.io.tmpdir") + "---");
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        /**
         * MultipartConfigElement参数含义
         * @param location the directory location where files will be stored (上传文件存储目录)
         * @param maxFileSize the maximum size allowed for uploaded files(单文件上传大小限制)
         * @param maxRequestSize the maximum size allowed for multipart/form-data requests(一个请求上传大小限制)
         * @param fileSizeThreshold the size threshold after which files will (文件写入磁盘的阈值，可理解为每次读取文件大小)
         */
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
        registration.setMultipartConfig(multipartConfigElement);
    }
}
