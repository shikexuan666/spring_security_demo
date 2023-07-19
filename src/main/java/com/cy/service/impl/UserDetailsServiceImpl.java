package com.cy.service.impl;

import com.cy.pojo.Authority;
import com.cy.pojo.Customer;
import com.cy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 表示用户权限校验的业务类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // 权限认证层依赖于用户模块的业务层
    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过业务层调用，查询用户的信息
        Customer customer = customerService.getCustomer(username);
        // 通过业务层调用，获取指定用户的权限信息
        List<Authority> authorities = customerService.getCustomerAuthority(username);

        // 对查询的用户权限进行封装，封装完成后交给安全框架来处理：List<SimpleGrantedAuthority>
        List<SimpleGrantedAuthority> list = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());

        // UserDetails对象表示封装了：当前的用户以及当前用户的权限信息，返回给安全框架
        if (customer != null) {
            UserDetails userDetails = new User(customer.getUsername(), customer.getPassword(), list);
            return userDetails;
        } else {
            // 表示登录的用户不存在，则抛出异常即可
            throw new UsernameNotFoundException("当前用户信息不存在！");
        }
    }
}
