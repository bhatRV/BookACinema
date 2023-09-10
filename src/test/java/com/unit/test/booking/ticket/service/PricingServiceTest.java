package com.unit.test.booking.ticket.service;

import com.rv.booking.ticket.BookACinemaApplication;
import com.rv.booking.ticket.entities.dto.Customer;
import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.entities.dto.Ticket;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.repository.DiscountRepository;
import com.rv.booking.ticket.repository.PriceRepository;
import com.rv.booking.ticket.service.impl.IndividualPricingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BookACinemaApplication.class)
public class PricingServiceTest {

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private IndividualPricingService individualPricingService;


    @Test
    public void shouldEstimatePriceForBookACinema_Individual_Cases_Successfully() {

        when(discountRepository.findByTicketType(TicketType.CHILD)).thenReturn(childDiscount());

        when(discountRepository.findByTicketType(TicketType.ADULT)).thenReturn(adultDiscount());

        when(priceRepository.findByMovieType(any())).thenReturn(originalPrice());


        //when( discountRepository.findByTicketType(any())).thenCallRealMethod();
        Customer userC = Customer.builder().age(10).name("Child Person").build();

        Customer userA = Customer.builder().age(40).name("Adult Person").build();

        CustomerRequest customerRequest = CustomerRequest.builder().transactionId(1)
                .customers(new ArrayList<>(Arrays.asList(userC, userA))).build();
        BigDecimal totalCost = BigDecimal.valueOf(0);

        List<Ticket> pricedTicket = new ArrayList<>();
        var result = individualPricingService.calculatePrice(TicketType.CHILD, new HashMap<TicketType, List<Ticket>>() {{
            put(TicketType.CHILD, new ArrayList<Ticket>());
            put(TicketType.ADULT, new ArrayList<Ticket>());

        }}, totalCost, pricedTicket);


        assertEquals(result, 1);


    }

    private PriceDetails originalPrice() {
        PriceDetails price = new PriceDetails();
        price.setPrice(BigDecimal.valueOf(100.00));
        return price;
    }

    private static Discounts childDiscount() {
        Discounts child = new Discounts();
        child.setDiscount(BigDecimal.valueOf(5.00));
        return child;
    }

    private static Discounts teenDiscount() {
        Discounts child = new Discounts();
        child.setDiscount(BigDecimal.valueOf(15.00));
        return child;
    }

    private static Discounts adultDiscount() {
        Discounts child = new Discounts();
        child.setDiscount(BigDecimal.valueOf(20.00));
        return child;
    }


    private static Discounts seniorDiscount() {
        Discounts child = new Discounts();
        child.setDiscount(BigDecimal.valueOf(25.00));
        return child;
    }

    private static Discounts familyDiscount() {
        Discounts child = new Discounts();
        child.setDiscount(BigDecimal.valueOf(10.00));
        return child;
    }

}
