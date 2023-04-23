package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Transformers.ProductTransfomer;
import com.example.ECommerce.dtos.requestDtos.ProductRequestDto;
import com.example.ECommerce.dtos.responseDtos.ProductResponseDto;
import com.example.ECommerce.entity.Product;
import com.example.ECommerce.entity.Seller;
import com.example.ECommerce.exceptions.SellerNotFound;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.repository.SellerRepository;
import com.example.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFound {

        Seller seller;
        try
        {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e)
        {
            throw new SellerNotFound("No Seller Found");
        }

        Product product=ProductTransfomer.productRequestDtoToProduct(productRequestDto);

        product.setSeller(seller);
        seller.getProductList().add(product);

        sellerRepository.save(seller);

        return ProductTransfomer.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();

        for(Product product : productList)
        {
            productResponseDtoList.add(ProductTransfomer.productToProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    @Override
    public String deleteProductBySeller(int sellerId, String productName) throws SellerNotFound {
        Seller seller;
        try
        {
            seller= sellerRepository.findById(sellerId).get();
        }
        catch (Exception e)
        {
            throw new SellerNotFound("No seller found");
        }

        List<Product> productList=seller.getProductList();

        for(Product product: productList)
        {
            if(product.getName().equals(productName))
            {
                productList.remove(product);
                productRepository.delete(product);

                sellerRepository.save(seller);

                return product.getName()+" deleted Successfully!";
            }
        }

        throw new RuntimeException("Product not found!");
    }

    @Override
    public ProductResponseDto updateProductPrice(int productId, int price) {
        Product product;
        try
        {
            product=productRepository.findById(productId).get();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Product Not Found");
        }

        product.setPrice(price);

        return ProductTransfomer.productToProductResponseDto(productRepository.save(product));
    }
}
