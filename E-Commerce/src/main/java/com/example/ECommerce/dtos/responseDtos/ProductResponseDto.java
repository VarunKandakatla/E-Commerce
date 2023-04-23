package com.example.ECommerce.dtos.responseDtos;

import com.example.ECommerce.Enum.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String name;
    String sellerName;
    @Enumerated(EnumType.STRING)
    Category category;
    int quantity;
    int price;
}
