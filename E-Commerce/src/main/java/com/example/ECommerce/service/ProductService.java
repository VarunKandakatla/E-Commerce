package com.example.ECommerce.service;


import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.dtos.requestDtos.ProductRequestDto;
import com.example.ECommerce.dtos.responseDtos.ProductResponseDto;
import com.example.ECommerce.exceptions.SellerNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFound;

    List<ProductResponseDto> getAllProducts();

    String deleteProductBySeller(int sellerId, String productName) throws SellerNotFound;

    ProductResponseDto updateProductPrice(int productId, int price);

    List<ProductResponseDto> getAllProductsofASeller(String companyName) throws SellerNotFound;

    Object get5CheaperProducts();

    Object getTop5CostliestProducts();

    Object getLessQuantityProducts(int quantity);

    Object getCheapProductsinCategory(Category category);

    Object getCostliestProductInCategory(Category category);

    Object getProductsInCategoryLessthanPrice(int price, String category);

}
