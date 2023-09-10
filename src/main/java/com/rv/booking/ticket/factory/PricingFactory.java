package com.rv.booking.ticket.factory;

import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.service.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class PricingFactory {
    private final List<PricingService> pricingServiceList;

    public PricingService findPricingService(final TicketType ticketType) {

        return pricingServiceList.stream()
                .filter(service -> service.supportType(ticketType))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
