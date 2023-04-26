package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Transformers.CustomerTransformer;
import com.example.ECommerce.Transformers.ItemTransformer;
import com.example.ECommerce.dtos.requestDtos.CustomerRequestDto;
import com.example.ECommerce.dtos.responseDtos.CustomerResponseDto;
import com.example.ECommerce.dtos.responseDtos.ItemResponseDto;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.exceptions.MobileNotFound;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        //creating customer & Cart
        Customer customer= CustomerTransformer.CustomerRequesttoCustomer(customerRequestDto);

        //saving and getting update customer
        Customer updateCustomer=customerRepository.save(customer);

       //response DTO
        CustomerResponseDto customerResponseDto=CustomerTransformer.customertoCustomerResponseDto(updateCustomer);

        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customerList=customerRepository.findAll();

        List<CustomerResponseDto> customerResponseDtoList=new ArrayList<>();

        for(Customer customer: customerList)
        {
            //Customer to customerResponseList using Transformer
            customerResponseDtoList.add(CustomerTransformer.customertoCustomerResponseDto(customer));
        }
        return customerResponseDtoList;

    }

    @Override
    public CustomerResponseDto updateNameOfCustomer(String name, String mobile) throws MobileNotFound {

       try
       {
           Customer customer=customerRepository.findByMobileNo(mobile);
           customer.setName(name);
           //customer to customerResponseDTO
           return CustomerTransformer.customertoCustomerResponseDto(customerRepository.save(customer));
       }
       catch (Exception e)
       {
           throw new MobileNotFound("Mobile number not exists");
       }

    }

    @Override
    public String deleteCustomer(String mobile) throws MobileNotFound {
        Customer customer=null;
        try
        {
            customer=customerRepository.findByMobileNo(mobile);
            customerRepository.delete(customer);
            return "Customer Deleted Successfully!";
        }
        catch (Exception e)
        {
            throw new MobileNotFound("Wrong Mobile number Entered");
        }

    }

}
