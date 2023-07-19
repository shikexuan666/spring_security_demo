package com.cy.service;

import com.cy.pojo.Authority;
import com.cy.pojo.Customer;

import java.util.List;

public interface CustomerService {

    // 根据用户名查询用户数据
    Customer getCustomer(String username);

    // 根据用户查询用户的权限信息
    List<Authority> getCustomerAuthority(String username);
}
