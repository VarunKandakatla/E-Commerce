package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Transformers.SellerTransformer;
import com.example.ECommerce.dtos.requestDtos.SellerRequestDto;
import com.example.ECommerce.dtos.responseDtos.SellerResponseDto;
import com.example.ECommerce.entity.Seller;
import com.example.ECommerce.exceptions.MobileNotFound;
import com.example.ECommerce.repository.SellerRepository;
import com.example.ECommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
        //saving seller
        Seller updatedSeller=sellerRepository.save(seller);
        //seller to seller ResponseDto
        return SellerTransformer.sellerToSellerResponseDto(updatedSeller);
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller> sellerList=sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtoList=new ArrayList<>();

        for(Seller seller: sellerList)
        {
            //adding all seller responses to the list
            sellerResponseDtoList.add(SellerTransformer.sellerToSellerResponseDto(seller));
        }
        return sellerResponseDtoList;
    }


    @Override
    public SellerResponseDto updateCompanyName(int id, String name) {

        try{
            Seller seller=sellerRepository.findById(id).get();
            seller.setEnterprise(name);
            //saving updated seller and return responseDto
            return SellerTransformer.sellerToSellerResponseDto(sellerRepository.save(seller));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Wrong Id Entered");
        }
    }

    @Override
    public String deleteSeller(int id, String mobNo) throws MobileNotFound {
        Seller seller=null;
        try
        {
             seller=sellerRepository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Alert! Wrong Id Entered");
        }

        //checking mobile number
        if(!seller.getMobileNo().equals(mobNo))
        {
            throw new MobileNotFound("Wrong mobile number entered!");
        }

        sellerRepository.delete(seller);
        return seller.getEnterprise()+" has been Deleted successfully!";
    }
}
