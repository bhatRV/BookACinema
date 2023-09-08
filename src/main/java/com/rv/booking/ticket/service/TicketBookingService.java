package com.rv.booking.ticket.service;

import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.entities.dto.CustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DiscountRepository;
import repository.PriceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketBookingService {
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PriceRepository priceRepository;

    public List<CustomerResponse> bookACinema(CustomerRequest customerRequest) {

        return null;
     }
}
