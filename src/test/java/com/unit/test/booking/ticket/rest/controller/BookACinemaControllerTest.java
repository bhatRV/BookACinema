package com.unit.test.booking.ticket.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rv.booking.ticket.BookACinemaApplication;
import com.rv.booking.ticket.entities.dto.Customer;
import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.mapper.MapStructMapper;
import com.rv.booking.ticket.repository.DiscountRepository;
import com.rv.booking.ticket.repository.PriceRepository;
import com.rv.booking.ticket.service.TicketBookingService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes=BookACinemaApplication.class)
public class BookACinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketBookingService ticketBookingService;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PriceRepository priceRepository;


    private MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);


    @Test
    void whenCallGetOffers_thenReturns200() throws Exception {
        mockMvc.perform(get("/v1/ticket/offers")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    void whenCallGetTicketPrices_thenReturns200() throws Exception {
        mockMvc.perform(get("/v1/ticket/price")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    void whenEmptyInput_thenReturns400_forTicketBooking() throws Exception {
        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json"))
                        .andExpect(status().is4xxClientError());

    }


    @Test
    void whenValidInputWithFamily_thenReturns200_forTicketBooking() throws Exception {


        String jsonInput = "{\n" +
                "\"transactionId\": 12345,\n" +
                "\"customers\": [\n" +
                "  {\n" +
                "\"name\":\"Daniel abc\",\n" +
                "\"age\": 10\n" +
                "},\n" +
                "{\n" +
                "\"name\":\"\",\n" +
                "\"age\": 10\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Mary Jones\",\n" +
                "\"age\": 10\n" +
                "},\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Michelle Parker\",\n" +
                "\"age\": 70\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "}";

        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("12345"))
                .andExpect(jsonPath("$.totalCost").value("28.75"));

    }

    @Test
    void whenValidInputWithChildAndAdultCase_thenReturns200_forTicketBooking() throws Exception {

        Customer userC = Customer.builder().age(10).name("Child Person").build();
        Customer userA = Customer.builder().age(40).name("Child Person").build();
        CustomerRequest customerRequest = CustomerRequest.builder().transactionId(1)
                .customers(new ArrayList<>(Arrays.asList(userC, userA))).build();


        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("1"))
                .andExpect(jsonPath("$.totalCost").value("30.0"));

    }


    @Test
    void whenValidInputWithOnly2Children_thenReturns200_forTicketBooking() throws Exception {


        String jsonInput = "{\n" +
                "\"transactionId\": 12345,\n" +
                "\"customers\": [\n" +
                "  {\n" +
                "\"name\":\"Daniel abc\",\n" +
                "\"age\": 10\n" +
                "},\n" +
                "{\n" +
                "\"name\":\"\",\n" +
                "\"age\": 10\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Mary Jones\",\n" +
                "\"age\": 40\n" +
                "},\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Michelle Parker\",\n" +
                "\"age\": 70\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "}";

        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("12345"))
                .andExpect(jsonPath("$.totalCost").value("52.5"));

    }

}
