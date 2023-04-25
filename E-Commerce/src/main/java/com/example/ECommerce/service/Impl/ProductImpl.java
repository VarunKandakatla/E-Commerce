package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Enum.Category;
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
import java.util.Collections;
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

    @Override
    public List<ProductResponseDto> getAllProductsofASeller(String companyName) throws SellerNotFound {

        Seller seller=sellerRepository.findByEnterprise(companyName);

        if(seller==null)
            throw new SellerNotFound("No Enterprise found!");

        List<Product> productList=seller.getProductList();
        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();

        for(Product product: productList)
        {
            productResponseDtoList.add(ProductTransfomer.productToProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    @Override
    public Object get5CheaperProducts() {

        List<ProductResponseDto> productResponseDtoList=getAllProducts();

        //sorting
        Collections.sort(productResponseDtoList,(a,b)->{
            return a.getPrice()-b.getPrice();
        });

        return productResponseDtoList.subList(0,5);

    }

    @Override
    public Object getTop5CostliestProducts() {
        List<ProductResponseDto> productResponseDtoList=getAllProducts();

        //Sorting based on price
        Collections.sort(productResponseDtoList,(a,b)->b.getPrice() - a.getPrice());

        //returning top 5
        return productResponseDtoList.subList(0,5);
    }

    @Override
    public Object getLessQuantityProducts(int quantity) {
        List<ProductResponseDto> productResponseDtoList =getAllProducts();
        List<ProductResponseDto> productResponseDtoList1=new ArrayList<>();

        Collections.sort(productResponseDtoList,(a,b)->a.getQuantity()-b.getQuantity());

        for(ProductResponseDto productResponseDto: productResponseDtoList)
        {
            if(productResponseDto.getQuantity()<=quantity)
            productResponseDtoList1.add(productResponseDto);
        }

        return productResponseDtoList1;
    }

    @Override
    public Object getCheapProductsinCategory(Category category) {

        List<Product> productList=productRepository.findAll();

        Collections.sort(productList,(a,b)-> a.getPrice()-b.getPrice());

        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();

        for(Product product : productList)
        {
            if(product.getCategory().equals(category))
                productResponseDtoList.add(ProductTransfomer.productToProductResponseDto(product));
        }

        return productResponseDtoList;
    }

    @Override
    public Object getCostliestProductInCategory(Category category) {

        List<Product> productList=productRepository.findAll();

        Collections.sort(productList,(a,b)-> b.getPrice()-a.getPrice());

        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();

        for(Product product : productList)
        {
            if(product.getCategory().equals(category))
                productResponseDtoList.add(ProductTransfomer.productToProductResponseDto(product));
        }

        return productResponseDtoList;
    }

    @Override
    public Object getProductsInCategoryLessthanPrice(int price, String category) {

        List<Product> productList=productRepository.findProductsBetweenRateinCategory(price,category);

        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();

        for(Product product : productList)
        {
            productResponseDtoList.add(ProductTransfomer.productToProductResponseDto(product));
        }

        return productResponseDtoList;
    }
}
