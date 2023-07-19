package com.cy.repository;


import com.cy.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA持久层的接口
 * */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * findByXxx: Xxx表示查询字段的条件，转化成where条件：where username=？
     * */
    Customer findByUsername(String username);
}
