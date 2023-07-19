package com.cy.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * security安全框架的配置类：
 * 继承WebSecurityConfigurerAdapter
 * 重写configure(AuthenticationManagerBuilder auth)
 * 开启security的安全配置的自定义支持，使用一个注解@EnableWebSecurity
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 定义数据源对象
    @Autowired   //  自动装配
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 自定义用户认证的管理
     * 参数：AuthenticationManagerBuilder 表示配置用户的权限认证方式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /**
         *基于内存认证：
         * 默认的登录页面：用户登录信息，用户名和密码
         * 如何在内存中声明用户信息？
         * 将用户内存中的信息关联上AuthenticationManagerBuilder对象
         * */


        /**
         // 获取指定的密码编码器
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         // inMemoryAuthentication()表示基于内存认证，用户的密码必须加密，指定密码的加密的方式
         auth.inMemoryAuthentication().passwordEncoder(encoder)
         // 设置用户名、设置按照指定密码器的初始化密码、设置了tom这个用户对应的角色（表示common目录下的资源可以被访问）
         .withUser("tom").password(encoder.encode("123456")).roles("common")
         .and() // 表示同时声明一组用户信息的初始化配置
         .withUser("jerry").password(encoder.encode("123456")).roles("vip");

         */

        // 使用JDBC进行身份认证
        // 查询数据库中的数据是否存在
        /**
        String userSql = "select username, password, valid from t_customer where username=?";
        // 查询用户权限
        String authentySql = "select c.username, a.authority, from t_customer c, t_authority a, t_customer_authority ca "
                + "where ca.customer_id=c.id and ca.authority_id=a.id and c.username=?";
        // 表示需要声明密码的编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 构建JDBC自定义认证配置
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .dataSource(dataSource) // 数据源
                .usersByUsernameQuery(userSql)  //根据指定的用户名查询用户的数据信息
                .authoritiesByUsernameQuery(authentySql); // 根据指定的权限进行查询
         */


        // userDetailService身份认证可以根据项目的MVC分层架构来实现对应的身份认证
        // 表示需要声明密码的编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}
