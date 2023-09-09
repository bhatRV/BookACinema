package com.rv.booking.ticket.service.impl;

import com.rv.booking.ticket.config.Constants;
import com.rv.booking.ticket.entities.dto.Ticket;
import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.repository.DiscountRepository;
import com.rv.booking.ticket.repository.PriceRepository;
import com.rv.booking.ticket.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class IndividualPricingService implements PricingService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PriceRepository priceRepository;


    @Override
    public Ticket calculatePrice(TicketType type, int count) {
        var discount = discountRepository.findByTicketType(type);
        var price = priceRepository.findByMovieType(Constants.DEFAULT);

        var totalPrice = price.getPrice().multiply(BigDecimal.valueOf(count));

        var finalPrice = totalPrice.subtract((totalPrice.multiply(discount.getDiscount()).divide(BigDecimal.valueOf(100))));

        return Ticket.builder().ticketType(type)
                .quantity(count)
                .cost(finalPrice).build();
    }

    @Override
    public boolean supportType(TicketType type) {
        return type == TicketType.ADULT
                || type == TicketType.CHILD
                || type == TicketType.SENIOR
                || type == TicketType.TEEN;

    }


}