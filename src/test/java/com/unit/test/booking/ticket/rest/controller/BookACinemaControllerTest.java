package com.unit.test.booking.ticket.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rv.booking.ticket.BookACinemaApplication;
import com.rv.booking.ticket.controller.BookACinemaController;
import com.rv.booking.ticket.entities.dto.Customer;
import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.mapper.MapStructMapper;
import com.rv.booking.ticket.service.TicketBookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookACinemaController.class)
@ContextConfiguration(classes=BookACinemaApplication.class)
public class BookACinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketBookingService ticketBookingService;


    private MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);


    @Test
    void whenEmptyInput_thenReturns200_forGetOffers() throws Exception {
        mockMvc.perform(get("/v1/ticket/offers")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    void whenEmptyInput_thenReturns200_forGetTicketPrices() throws Exception {
        mockMvc.perform(get("/v1/ticket/price")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    void whenEmptyInput_thenReturns200_forTicketBooking() throws Exception {
        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    void whenValidInput_thenReturns200_forTicketBooking() throws Exception {

        Customer userC = Customer.builder().age(10).name("Child Person").build();
        Customer userA = Customer.builder().age(40).name("Child Person").build();
        CustomerRequest customerRequest = CustomerRequest.builder().transactionId(1)
                .customers(new ArrayList<>(Arrays.asList(userC, userA))).build();



        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void whenInValidInput_thenReturns400_forTicketBooking() throws Exception {

        Customer userC = Customer.builder().age(10).name("Child Person").build();
        Customer userA = Customer.builder().age(40).name("Child Person").build();
        CustomerRequest customerRequest = CustomerRequest.builder().transactionId(1)
                .customers(new ArrayList<>(Arrays.asList(userC, userA))).build();



        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().is4xxClientError());
    }
}
