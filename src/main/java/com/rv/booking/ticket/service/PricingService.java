package com.rv.booking.ticket.service;

import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DiscountRepository;
import repository.PriceRepository;

import java.util.List;

@Service
public class PricingService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PriceRepository priceRepository;


    public List<Discounts> getAllOffers() {
        return discountRepository.findAll();
    }


    public List<PriceDetails> getAllPriceDetails() {
        return priceRepository.findAll();
    }
}
