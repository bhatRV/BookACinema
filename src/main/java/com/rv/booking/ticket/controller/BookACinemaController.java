package com.rv.booking.ticket.controller;

import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.entities.dto.CustomerResponse;
import com.rv.booking.ticket.entities.dto.DiscountDTO;
import com.rv.booking.ticket.entities.dto.PriceDTO;
import com.rv.booking.ticket.mapper.MapStructMapper;
import com.rv.booking.ticket.service.PricingService;
import com.rv.booking.ticket.service.TicketBookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    private MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @Operation(summary = "Get all Offers")
    @GetMapping("offers")
    public ResponseEntity<List<DiscountDTO>> getAllOffers() {

        return ResponseEntity.ok(
                INSTANCE.toDiscountsDTOs(pricingService.getAllOffers())
        );
    }

    @Operation(summary = "Get all prices")
    @GetMapping("price")
    public ResponseEntity<List<PriceDTO>> getAllPriceDetails() {

        return ResponseEntity.ok(
                INSTANCE.toPriceDTOs(pricingService.getAllPriceDetails())
        );

    }

    @Operation(summary = "Findout the pricing details for your booking")
    @PostMapping("book")
    public List<CustomerResponse> bookACinema(CustomerRequest customerRequest) {
        return ticketBookingService.bookACinema(customerRequest);

    }



}