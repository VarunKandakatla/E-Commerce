package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Transformers.ItemTransformer;
import com.example.ECommerce.dtos.requestDtos.ItemRequestDto;
import com.example.ECommerce.dtos.responseDtos.ItemResponseDto;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.entity.Product;
import com.example.ECommerce.exceptions.CustomerNotFound;
import com.example.ECommerce.exceptions.ProductNotFound;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.repository.ItemRepository;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ItemResponseDto addItem(ItemRequestDto itemRequestDto) throws CustomerNotFound, ProductNotFound {

        //Checking customer is Valid or not

        Customer customer;
        try
        {
            customer=customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }catch (Exception e)
        {
            throw new CustomerNotFound("Invalid Customer");
        }

        //Checking the product is Valid or not
        Product product;
        try
        {
            product=productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductNotFound("Invalid Product Details");
        }

        //Checking the reqQuantity is Available or not

        if(product.getQuantity()<itemRequestDto.getReqQuantity())
        {
            throw new ProductNotFound("Quantity is Exceeded! Not Available");
        }


        //checking whether the item is already exists or not
       Items items =ItemTransformer.Check(customer,itemRequestDto);

        if(items!=null)
        {
            return ItemTransformer.ItemtoItemResponseDto(itemRepository.save(items));
        }

        //fresh cart or Item not exists before
        items=Items.builder()
                .name(product.getName())
                .price(product.getPrice())
                .reqQuantity(itemRequestDto.getReqQuantity())
                .product(product)
                .cart(customer.getCart())
                .build();
        //Setting cart Value
        customer.getCart().setCartValue(customer.getCart().getCartValue()+(items.getPrice()*items.getReqQuantity()));

        return ItemTransformer.ItemtoItemResponseDto(itemRepository.save(items));
    }

    @Override
    public Object getAllItems() {
        List<Items> itemsList=itemRepository.findAll();

        List<ItemResponseDto> itemResponseDtoList=new ArrayList<>();

        for(Items items : itemsList)
        {
            itemResponseDtoList.add(ItemTransformer.ItemtoItemResponseDto(items));
        }
        return itemResponseDtoList;
    }

    @Override
    public void deleteAll() {
        List<Customer> customerList=customerRepository.findAll();

        for(Customer customer: customerList)
        {
            customer.getCart().setCartValue(0);
        }

        itemRepository.deleteAll();
    }

    @Override
    public Object getListofCartsWithProduct(int productId) {

        List<Integer> cartIds = itemRepository.findCartsofProduct(productId);

       return "TotalCarts in which product exists is: "+cartIds.size()+"\nCarts are: "+cartIds.toString();
    }



}
