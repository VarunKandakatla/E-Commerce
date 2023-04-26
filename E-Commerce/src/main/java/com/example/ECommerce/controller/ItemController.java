package com.example.ECommerce.controller;

import com.example.ECommerce.dtos.requestDtos.ItemRequestDto;
import com.example.ECommerce.dtos.responseDtos.ItemResponseDto;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Items;
import com.example.ECommerce.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity addItem(@RequestBody ItemRequestDto itemRequestDto)
    {
        try
        {
//            Items items = itemService.addItem(itemRequestDto);
            return new ResponseEntity<>(itemService.addItem(itemRequestDto), HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllItems")
    public ResponseEntity getAllItems()
    {
        return new ResponseEntity<>(itemService.getAllItems(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getListOfCartsWithProduct")
    public ResponseEntity getListofCartsWithProduct(@RequestParam int productId)
    {
        return new ResponseEntity(itemService.getListofCartsWithProduct(productId),HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAll")
    public String DeleteAll()
    {
        itemService.deleteAll();
        return "Deleted Successfully";
    }

}
