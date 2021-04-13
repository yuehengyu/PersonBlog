package com.yhy.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类， 配置拦截器的pattern
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截admin开头的所有请求， 但是需要排除掉login请求
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/*")
                .addPathPatterns("/admin/blogs/createBlog")
                .addPathPatterns("/admin/blogs/*/updateBlog")
                .addPathPatterns("admin/tags/addTag")
                .addPathPatterns("/admin/category/addCategory")
                .excludePathPatterns("/admin") //排除login请求
                .excludePathPatterns("/admin/login"); //排除login请求



    }
}
