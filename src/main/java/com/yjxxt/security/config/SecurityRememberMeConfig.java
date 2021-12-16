package com.yjxxt.security.config;

import com.yjxxt.security.config.handler.SpringSecurityAccessDeniedHandler;
import com.yjxxt.security.config.handler.SpringSecurityAuthenticationFailureHandler;
import com.yjxxt.security.config.handler.SpringSecurityAuthenticationSuccessHandler;
import com.yjxxt.security.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;

/**
 * SpringSecurity 配置类
 *   Secured 注解配置
 */
//@Configuration
public class SecurityRememberMeConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SpringSecurityAuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource
    private SpringSecurityAuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private SpringSecurityAccessDeniedHandler accessDeniedHandler;

    @Resource
    private UserServiceImpl userService;

    @Resource
    private PersistentTokenRepository persistentTokenRepository;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf 配置
        http.csrf().disable();

        // 配置表单处理信息  登录页&后端登录请求地址&登录成功页面
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                // 登录成功Handler 处理
                .successHandler(authenticationSuccessHandler)
                // 登录失败Handler 处理
                .failureHandler(authenticationFailureHandler)
                // 设置登录表单参数名称
                .usernameParameter("userName")
                .passwordParameter("userPwd");


        // 配置SpringSecurity 权限
        http.authorizeRequests()
                // 配置放行资源
                .antMatchers("/login.html","/login","/error.html","/js/**").permitAll()
                // 其他资源必须登录才能访问
                .anyRequest().authenticated();



        // 配置免登陆
        http.rememberMe()
                .userDetailsService(userService)
                .tokenRepository(persistentTokenRepository)
                // 设置免登陆失效时间 7天
                .tokenValiditySeconds(60*60*24*7);


        // 403 访问受限处理
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }
}
