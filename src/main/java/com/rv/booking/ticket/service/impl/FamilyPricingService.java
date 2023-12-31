package com.rv.booking.ticket.service.impl;

import com.rv.booking.ticket.entities.dto.Ticket;
import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.repository.DiscountRepository;
import com.rv.booking.ticket.repository.PriceRepository;
import com.rv.booking.ticket.service.PricingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyPricingService implements PricingService {
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public BigDecimal calculatePrice(TicketType type, Map<TicketType, List<Ticket>> ticketMap, BigDecimal totalCost, List<Ticket> pricedTicket) {
        var countOfTicket = ticketMap.get(TicketType.CHILD).size();

        Ticket ticket = calculatePrice(TicketType.FAMILY, countOfTicket);
        pricedTicket.add(ticket);

        totalCost = totalCost.add(ticket.getCost());
        log.info("Family Pricing completed for type:{} totalCost:{}",type,totalCost);

        return totalCost;

    }


    public Ticket calculatePrice(TicketType type, int count) {
        var discount = discountRepository.findByTicketType(TicketType.FAMILY);
        var price = priceRepository.findByTicketType(TicketType.CHILD);

        var totalPrice = price.getPrice().multiply(BigDecimal.valueOf(count));

        var finalPrice = totalPrice.subtract((totalPrice.multiply(discount.getDiscount()).divide(BigDecimal.valueOf(100))));


        return Ticket.builder().ticketType(TicketType.CHILD)
                .quantity(count)
                .cost(finalPrice).build();
    }

    @Override
    public boolean supportType(TicketType type) {
        return type == TicketType.FAMILY;

    }


}