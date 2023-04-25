package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select * from product where price < :price AND category = :category",nativeQuery = true)
    List<Product> findProductsBetweenRateinCategory(int price, String category);
}
