package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Cards,Integer> {

    Cards findByCardNo(String cardNo);
}
