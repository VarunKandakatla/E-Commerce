package com.example.ECommerce.controller;

import com.example.ECommerce.dtos.requestDtos.CustomerRequestDto;
import com.example.ECommerce.dtos.responseDtos.CustomerResponseDto;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.exceptions.MobileNotFound;
import com.example.ECommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto)
    {
        return customerService.addCustomer(customerRequestDto);
    }

    @PutMapping("/updateName")
    public ResponseEntity updateNameOfCustomer(@RequestParam("name") String name, String mobile)
    {
        try{
            return new ResponseEntity<>(customerService.updateNameOfCustomer(name,mobile), HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAllCustomers")
    public List<CustomerResponseDto> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("mobile") String mobile){
        try{
            return customerService.deleteCustomer(mobile);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }

    }


}
