package com.example.ECommerce.dtos.responseDtos;

import com.example.ECommerce.Enum.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {

    String customerName;
    String cardNo;
    @Enumerated(EnumType.STRING)
    CardType cardType;

}
