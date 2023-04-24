package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmail(String mailId);

    Seller findByEnterprise(String companyName);
}
