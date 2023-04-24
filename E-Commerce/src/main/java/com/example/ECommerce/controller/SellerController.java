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
    //Get seller by email
    @GetMapping("/getByEmail")
    public ResponseEntity getSellerbyEmail(@RequestParam String mailId)
    {
        try
        {
            return new ResponseEntity<>(sellerService.getSellerbyEmail(mailId),HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //Get Seller by ID
    @GetMapping("/getSellerbyId/{id}")
    public ResponseEntity getSellerbyId(@PathVariable("id") int id)
    {
        try
        {
            return new ResponseEntity(sellerService.getSellerbyId(id),HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //Getting all sellers
    @GetMapping("/getAllSellers")
    public List<SellerResponseDto> getAllSellers()
    {
        return sellerService.getAllSellers();
    }
    //updating Seller info based on emailId
    @PutMapping("/updateSellerDetails/{emailID}")
    public ResponseEntity updateSellerDetailsByEmail(@PathVariable("emailID") String mailId, @RequestBody SellerRequestDto sellerRequestDto)
    {
        try
        {
           return new ResponseEntity<>(sellerService.updateSellerDetailsByEmail(mailId,sellerRequestDto),HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //Updating company name
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
//Deleting Seller by EmailID;
    @DeleteMapping("/deleteSellerbyEmail")
    public ResponseEntity deleteSellerByEmail(@RequestParam String emailID)
    {
        try
        {
            return new ResponseEntity(sellerService.deleteSellerbyEmail(emailID),HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
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
