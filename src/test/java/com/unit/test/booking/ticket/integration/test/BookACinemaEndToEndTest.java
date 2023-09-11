package com.unit.test.booking.ticket.integration.test;

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
public class BookACinemaEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketBookingService ticketBookingService;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PriceRepository priceRepository;


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

    @Test
    void whenValidInputAsPerScenario1_thenReturns200_forTicketBooking() throws Exception {


        String jsonInput = "{\n" +
                "\"transactionId\": 1,\n" +
                "\"customers\": [\n" +
                "{\n" +
                "\"name\": \"John Smith\",\n" +
                "\"age\": 70\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Jan Doe\",\n" +
                "\"age\": 5\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Bob Doe\",\n" +
                "\"age\": 6\n" +
                "}\n" +
                "]\n" +
                "}";

        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("1"))
                .andExpect(jsonPath("$.totalCost").value("27.5"));

    }

    @Test
    void whenValidInputAsPerScenario2_thenReturns200_forTicketBooking() throws Exception {


        String jsonInput = "{\n" +
                "\"transactionId\": 2,\n" +
                "\"customers\": [\n" +
                "{\n" +
                "\"name\":\"Billy Kidd\",\n" +
                "\"age\": 36\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Zoe Daniels\",\n" +
                "\"age\": 3\n" +
                "},\n" +
                "{\n" +
                "\"name\":\"George White\",\n" +
                "\"age\": 8\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\"name\":\"Tommy Anderson\",\n" +
                "\"age\": 9\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Joe Smith\",\n" +
                "\"age\": 17\n" +
                "}\n" +
                "]\n" +
                "}";

        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("2"))
                .andExpect(jsonPath("$.totalCost").value("48.25"));

    }

    @Test
    void whenValidInputAsPerScenario3_thenReturns200_forTicketBooking() throws Exception {


        String jsonInput = "{\n" +
                "\"transactionId\": 3,\n" +
                "\"customers\": [\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Jesse James\",\n" +
                "\"age\": 36\n" +
                "},\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Daniel Anderson\",\n" +
                "\"age\": 95\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"MaryJones\",\n" +
                "\"age\": 15\n" +
                "},\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Michelle Parker\",\n" +
                "\"age\": 10\n" +
                "}\n" +
                "]\n" +
                "}";

        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("3"))
                .andExpect(jsonPath("$.totalCost").value("59.5"));

    }

    @Test
    void whenInValidInputMissingTransactionId_thenReturns400_forTicketBooking() throws Exception {

        String jsonInput = "{\n" +
                "\"customers\": [\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Jesse James\",\n" +
                "\"age\": 36\n" +
                "},\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Daniel Anderson\",\n" +
                "\"age\": 95\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"MaryJones\",\n" +
                "\"age\": 15\n" +
                "},\n" +
                "{\n" +
                "\"name\":\n" +
                "\"Michelle Parker\",\n" +
                "\"age\": 10\n" +
                "}\n" +
                "]\n" +
                "}";

        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(jsonInput))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void whenInValidInputMissingAge_thenReturns400_forTicketBooking() throws Exception {

        String jsonInput = "{\n" +
                "\"transactionId\": 2,\n" +
                "\"customers\": [\n" +
                "{\n" +
                "\"name\":\"Billy Kidd\",\n" +
                "\"age\": 36\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Zoe Daniels\",\n" +
                "\"age\": 3\n" +
                "},\n" +
                "{\n" +
                "\"name\":\"George White\"\n" +

                "},\n" +
                "\n" +
                "{\n" +
                "\"name\":\"Tommy Anderson\",\n" +
                "\"age\": 9\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Joe Smith\",\n" +
                "\"age\": 17\n" +
                "}\n" +
                "]\n" +
                "}";

        mockMvc.perform(post("/v1/ticket/book")
                        .contentType("application/json")
                        .content(jsonInput))
                .andExpect(status().is4xxClientError());
    }
}
