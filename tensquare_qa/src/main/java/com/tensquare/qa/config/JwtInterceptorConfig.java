package com.tensquare.qa.config;

import com.tensquare.qa.intercept.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/12 13:31
 * @version: v1.0.0
 */
@Configuration
public class JwtInterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/*/login/**")
                .excludePathPatterns("/user/register/**");
    }
}
