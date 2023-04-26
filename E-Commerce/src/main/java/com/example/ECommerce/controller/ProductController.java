package com.example.ECommerce.controller;

import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.dtos.requestDtos.ProductRequestDto;
import com.example.ECommerce.dtos.responseDtos.ProductResponseDto;
import com.example.ECommerce.exceptions.SellerNotFound;
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
//Adding Product
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
    //Getting all product of a seller
    @GetMapping("/getAllProductsofaSeller")
    public ResponseEntity getAllProductsOfaSeller(@RequestParam String companyName)  {
        try{
            return new ResponseEntity<>(productService.getAllProductsofASeller(companyName),HttpStatus.ACCEPTED);
        }
       catch (Exception e)
       {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
    //TOP 5 Cheapest Products
    @GetMapping("/getTop5CheaperProducts")
    public ResponseEntity get5CheaperProducts()
    {
        return new ResponseEntity<>(productService.get5CheaperProducts(),HttpStatus.ACCEPTED);
    }

    //Top 5 Costliest Products
    @GetMapping("/getTop5CostliestProducts")
    public ResponseEntity getTop5Products()
    {
        return new ResponseEntity(productService.getTop5CostliestProducts(),HttpStatus.ACCEPTED);
    }

    //return all products quanity <
    @GetMapping("/getLessQuantityProducts")
    public ResponseEntity getLessQuantityProducts(@RequestParam int quantity)
    {
        return new ResponseEntity(productService.getLessQuantityProducts(quantity),HttpStatus.ACCEPTED);
    }

//    Cheap product in particular category
    @GetMapping("/getCheapProductsInCategory/{name}")
    public ResponseEntity getCheapProductsinCategory(@PathVariable("name")Category category)
    {
        return new ResponseEntity(productService.getCheapProductsinCategory(category),HttpStatus.ACCEPTED);
    }
//CUSTOM QUERY
    @GetMapping("/getProductsInCategoryLessthanPrice")
    public ResponseEntity getProductsInCategoryLessthanPrice(@RequestParam int price, String category)
    {
        return new ResponseEntity<>(productService.getProductsInCategoryLessthanPrice(price,category),HttpStatus.ACCEPTED);
    }

//    Costly product in particular category
    @GetMapping("/getCostliestProductInCategory")
    public ResponseEntity getCostliestProductInCategory(@RequestParam Category category)
    {
        return new ResponseEntity(productService.getCostliestProductInCategory(category),HttpStatus.ACCEPTED);
    }

//Getting all products
    @GetMapping("/getAllProducts")
    public List<ProductResponseDto> getAllProducts()
    {
        return productService.getAllProducts();
    }

//Updating price
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

//delete product
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
