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
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
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


    @Before
    private void setUp() {
     }



    @Test
    public void shouldCallGetAllOffersSuccessfully() {
        when(discountRepository.findAll()).thenReturn(new ArrayList<>());

         var result = ticketBookingService.getAllOffers();

        verify(discountRepository).findAll();

    }



    @Test
    public void shouldCallGetAllPricesSuccssefully() {
        when(priceRepository.findAll()).thenReturn(new ArrayList<>());

        var result = ticketBookingService.getAllPriceDetails();

        verify(priceRepository).findAll();
    }



  @Test
    public void shouldEstimatePriceForBookACinemaSuccessfully() {

        when(priceRepository.findByMovieType(any())).thenReturn(new PriceDetails());
        when(discountRepository.findByTicketType(any())).thenReturn(new Discounts());

      Customer userC = Customer.builder().age(10).name("Child Person").build();
      Customer userA = Customer.builder().age(40).name("Child Person").build();
      CustomerRequest customerRequest = CustomerRequest.builder().transactionId(1)
              .customers(new ArrayList<>(Arrays.asList(userC, userA))).build();

      ticketBookingService.bookACinema(customerRequest);

      verify(priceRepository).findByMovieType(any());
      verify(discountRepository).findByTicketType(any());

  }

}
