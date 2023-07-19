package com.cy.service.impl;

import com.cy.pojo.Authority;
import com.cy.pojo.Customer;
import com.cy.repository.AuthorityRepository;
import com.cy.repository.CustomerRepository;
import com.cy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    // 使用唯一的用户名来查询用户的信息
    @Override
    public Customer getCustomer(String username) {
        Customer customer = customerRepository.findByUsername(username);
        return customer;
    }

    // 使用当前唯一的用户名查询用户的权限信息
    @Override
    public List<Authority> getCustomerAuthority(String username) {
        List<Authority> authorities = authorityRepository.findAuthoritiesByUsername(username);
        return authorities;
    }
}
