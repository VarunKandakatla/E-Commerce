package com.example.ECommerce.controller;

import com.example.ECommerce.dtos.requestDtos.SellerRequestDto;
import com.example.ECommerce.dtos.responseDtos.SellerResponseDto;
import com.example.ECommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public SellerResponseDto addSeller(@RequestBody SellerRequestDto sellerRequestDto)
    {
        return sellerService.addSeller(sellerRequestDto);
    }

    @GetMapping("/getAllSellers")
    public List<SellerResponseDto> getAllSellers()
    {
        return sellerService.getAllSellers();
    }

    @PutMapping("/updateCompanyName")
    public ResponseEntity updateCompanyName(@RequestParam int id, String name)
    {
        try
        {
            return new ResponseEntity<>(sellerService.updateCompanyName(id,name), HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteSeller/{id}/{mobile}")
    public ResponseEntity deleteSeller(@PathVariable("id") int id,@PathVariable("mobile") String mobNo)
    {
        try
        {
            return new ResponseEntity<>(sellerService.deleteSeller(id,mobNo),HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.ALREADY_REPORTED);
        }
    }
}
