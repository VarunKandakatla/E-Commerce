package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Items,Integer> {
    @Query(value = "select cart_id from items  where product_id = :productId",nativeQuery = true)
    List<Integer> findCartsofProduct(int productId);

}
