package com.example.ECommerce.dtos.requestDtos;

import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.entity.Seller;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    String productName;
    @Enumerated(EnumType.STRING)
    Category category;
    int quantity;
    int price;
    int sellerId;
}
