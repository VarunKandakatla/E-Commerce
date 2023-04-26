package com.example.ECommerce.dtos.requestDtos;

import com.example.ECommerce.Enum.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {

    String cardNo;
    String expiryDate;
    int cvv;
    @Enumerated(EnumType.STRING)
    CardType cardType;
    int customerId;
}
