package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.annotation.ServletSecurity;

/**
 * @Description: 安全配置类
 * @author: Arnold
 * @since: 2019/5/12 7:50
 * @version: v1.0.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()    //所有全注解配置实现的开端，表示开始说明需要权限
                //需要的权限分为两部分，1.拦截路径，2.访问该了路径的权限
                .antMatchers("/**").permitAll() //表示拦截什么路径，任何权限都可以放行
                .anyRequest().authenticated()   //任何请求认证后才可以通过
                .and().csrf().disable();    //表示csrf拦截失效
    }
}
