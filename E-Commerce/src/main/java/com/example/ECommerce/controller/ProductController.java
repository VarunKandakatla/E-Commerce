package com.example.ECommerce.controller;

import com.example.ECommerce.dtos.requestDtos.ProductRequestDto;
import com.example.ECommerce.dtos.responseDtos.ProductResponseDto;
import com.example.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        try
        {
            return new ResponseEntity<>(productService.addProduct(productRequestDto), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllProducts")
    public List<ProductResponseDto> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @PutMapping("/updatePrice")
    public ResponseEntity updatePrice(@RequestBody int productId, int price)
    {
        try
        {
            return new ResponseEntity<>(productService.updateProductPrice(productId,price),HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity deleteProductbySeller(@RequestParam int sellerId, String productName )
    {
        try
        {
            return new ResponseEntity<>(productService.deleteProductBySeller(sellerId,productName),HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
