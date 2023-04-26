package com.example.ECommerce.service.Impl;

import com.example.ECommerce.Transformers.CardTransformer;
import com.example.ECommerce.dtos.requestDtos.CardRequestDto;
import com.example.ECommerce.entity.Cards;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Seller;
import com.example.ECommerce.exceptions.CardAlreadyExists;
import com.example.ECommerce.repository.CardRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Object addCard(CardRequestDto cardRequestDto) throws CardAlreadyExists {

        Customer customer;
                try {
                    customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
                }catch (Exception e)
                {
                    throw new RuntimeException("Customer not found");
                }
               //Checking card duplicate
        Cards cards=cardRepository.findByCardNo(cardRequestDto.getCardNo());

                if(cards!=null)
                {
                    throw new CardAlreadyExists("Duplicate entry! Card Already Exists");
                }

        cards = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        //setting customer & card
        cards.setCustomer(customer);
        customer.getCardsList().add(cards);
        customerRepository.save(customer);

        return CardTransformer.cardsToCardResponseDto(cards);

    }
}
