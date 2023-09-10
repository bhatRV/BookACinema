package com.unit.test.booking.ticket.service;


import com.rv.booking.ticket.BookACinemaApplication;
import com.rv.booking.ticket.entities.dto.Customer;
import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import com.rv.booking.ticket.factory.PricingFactory;
import com.rv.booking.ticket.repository.DiscountRepository;
import com.rv.booking.ticket.repository.PriceRepository;
import com.rv.booking.ticket.service.TicketBookingService;
import com.rv.booking.ticket.service.impl.IndividualPricingService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BookACinemaApplication.class)
public class TicketBookingServiceTest {

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PricingFactory pricingFactory;

    @InjectMocks
    private TicketBookingService ticketBookingService;

    @Value("${family.min.kids}")
    private int countOfKidsForFamily;

    @MockBean
    IndividualPricingService individualPricingService;

    @Before
    private void setUp() {
    }


    @Test
    public void shouldCallGetAllOffersSuccessfully() {
        when(discountRepository.findAll()).thenReturn(Collections.singletonList(new Discounts()));

        var result = ticketBookingService.getAllOffers();

        verify(discountRepository).findAll();

        assertEquals(result.size(), 1);

    }


    @Test
    public void shouldCallGetAllPricesSuccssefully() {
        when(priceRepository.findAll()).thenReturn(Collections.singletonList(new PriceDetails()));

        var result = ticketBookingService.getAllPriceDetails();

        verify(priceRepository).findAll();

        assertEquals(result.size(), 1);

    }


    @Test
    public void shouldEstimatePriceForBookACinemaSuccessfully() {

        when(discountRepository.findByTicketType(any())).thenReturn(new Discounts());

        when(pricingFactory.findPricingService(any())).thenReturn(individualPricingService);

        Customer userC = Customer.builder().age(10).name("Child Person").build();
        Customer userA = Customer.builder().age(40).name("Child Person").build();

        CustomerRequest customerRequest = CustomerRequest.builder().transactionId(1)
                .customers(new ArrayList<>(Arrays.asList(userC, userA))).build();

        ticketBookingService.bookACinema(customerRequest);
        verify(pricingFactory, atLeast(2)).findPricingService(any());

    }


}
