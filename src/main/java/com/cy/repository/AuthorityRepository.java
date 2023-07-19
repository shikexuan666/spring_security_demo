package com.cy.repository;

import com.cy.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    /**
     * 注解Query：表示给JPA的持久层接口方法来映射一个自定义的SQL语句
     * value：表示当前的SQL语句
     * nativeQuery：表示开启SQL语句的传统写法
     * 动态SQL语句的映射规则：将接口中的方法的参数列表对应的每一个参数通过编号来设置，从1开始取值，即使参数列表中只有一个参数，编号也不能省略
     */
    @Query(value = "select a.*, from t_customer c, t_authority a, t_customer_authority ca " +
            "where ca.customer_id=c.id and ca.authority_id=a.id and c.username=?1", nativeQuery = true)
    List<Authority> findAuthoritiesByUsername(String username);


}
