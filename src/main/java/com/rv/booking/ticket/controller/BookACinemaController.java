package com.rv.booking.ticket.controller;

import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.entities.dto.CustomerResponse;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import com.rv.booking.ticket.service.PricingService;
import com.rv.booking.ticket.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author  Rashmi Vishnu
 * This is the Controller Which Provides REST APIs to book tickets for the cinema listed in out website.
 */
@RestController
@RequestMapping(path = "/v1/ticket/")
public class BookACinemaController {
    @Autowired
    private TicketBookingService ticketBookingService;

    @Autowired
    private PricingService pricingService;

    @GetMapping("offers")
    public List<Discounts> getAllOffers() {
        return pricingService.getAllOffers();
    }


    @GetMapping("price")
    public List<PriceDetails> getAllPriceDetails() {
        return pricingService.getAllPriceDetails();
    }


    @PostMapping("book")
    public List<CustomerResponse> bookACinema(CustomerRequest customerRequest) {
        return ticketBookingService.bookACinema(customerRequest);

    }



}