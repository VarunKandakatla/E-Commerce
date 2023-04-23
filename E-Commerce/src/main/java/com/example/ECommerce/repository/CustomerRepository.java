package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByMobileNo(String mobile);
    Customer findByEmail(String mail);
}
