package com.example.ECommerce.Transformers;

import com.example.ECommerce.Enum.ProductStatus;
import com.example.ECommerce.dtos.requestDtos.ProductRequestDto;
import com.example.ECommerce.dtos.responseDtos.ProductResponseDto;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.entity.Product;

import java.util.List;

public class ProductTransfomer {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        Product product= Product.builder()
                .name(productRequestDto.getProductName())
                .category(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .price(productRequestDto.getPrice())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product product)
    {
        ProductResponseDto productResponseDto= ProductResponseDto.builder()
                .category(product.getCategory())
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .name(product.getSeller().getEnterprise())
                .build();

        return productResponseDto;
    }

    public static ProductResponseDto ItemToProductResponseDto(Items items)
    {
        return ProductResponseDto.builder()
                .productName(items.getName())
                .name(items.getCart().getCustomer().getName())
                .quantity(items.getReqQuantity())
                .category(items.getProduct().getCategory())
                .price(items.getPrice()).build();
    }

    public static void ReduceQuantity(List<Items> itemsList) throws Exception {
        for(Items items: itemsList)
        {
            if(items.getReqQuantity()>items.getProduct().getQuantity())
            {
                throw new Exception();
            }
            else
            {
                Product product = items.getProduct();
                int updateQuantity= product.getQuantity()-items.getReqQuantity();
                product.setQuantity(updateQuantity);

                if(product.getQuantity()==0)
                {
                    product.setProductStatus(ProductStatus.OUT_OF_STOCK);
                }
            }
        }
    }
}
