package com.example.ECommerce.Transformers;

import com.example.ECommerce.dtos.requestDtos.ProductRequestDto;
import com.example.ECommerce.dtos.responseDtos.ProductResponseDto;
import com.example.ECommerce.entity.Product;

public class ProductTransfomer {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        Product product= Product.builder()
                .name(productRequestDto.getProductName())
                .category(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .price(productRequestDto.getPrice())
                .build();

        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product product)
    {
        ProductResponseDto productResponseDto= ProductResponseDto.builder()
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .sellerName(product.getSeller().getEnterprise())
                .build();

        return productResponseDto;
    }
}
