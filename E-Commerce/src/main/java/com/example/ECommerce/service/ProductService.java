package com.example.ECommerce.service;


import com.example.ECommerce.dtos.requestDtos.ProductRequestDto;
import com.example.ECommerce.dtos.responseDtos.ProductResponseDto;
import com.example.ECommerce.exceptions.SellerNotFound;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFound;

    List<ProductResponseDto> getAllProducts();

    String deleteProductBySeller(int sellerId, String productName) throws SellerNotFound;

    ProductResponseDto updateProductPrice(int productId, int price);
}
