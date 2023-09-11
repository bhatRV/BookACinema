package com.rv.booking.ticket.service;

import com.rv.booking.ticket.entities.dto.Customer;
import com.rv.booking.ticket.entities.dto.CustomerRequest;
import com.rv.booking.ticket.entities.dto.CustomerResponse;
import com.rv.booking.ticket.entities.dto.Ticket;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.factory.PricingFactory;
import com.rv.booking.ticket.repository.DiscountRepository;
import com.rv.booking.ticket.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static com.rv.booking.ticket.entities.model.AgeRange.ADULT;
import static com.rv.booking.ticket.entities.model.AgeRange.CHILD;
import static com.rv.booking.ticket.entities.model.AgeRange.TEEN;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketBookingService {
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PricingFactory pricingFactory;

    @Value("${family.min.kids}")
    private int countOfKidsForFamily;

    BiPredicate<Map<TicketType, List<Ticket>>, TicketType> isFamily = (ticketMap1, category1) ->
            category1.name().equals(TicketType.CHILD.name())  && ticketMap1.get(TicketType.CHILD).size() >= countOfKidsForFamily;

    public List<Discounts> getAllOffers() {
        return discountRepository.findAll();
    }

    public List<PriceDetails> getAllPriceDetails() {
        return priceRepository.findAll();
    }


    public CustomerResponse bookACinema(CustomerRequest customerRequest) {

        if(customerRequest == null || customerRequest.getTransactionId() == null)
        {
            log.error("An Error Occurred while processing teh request. Validation Failed, CustomerRequest is empty or Transaction Id is missing ");

            throw new IllegalArgumentException("Invalid Input with Empty/Null Request Object or field");
        }

        List<Customer> customerList = customerRequest.getCustomers();

         Map<TicketType, List<Ticket>> ticketMap = customerList.stream()
                .map(x -> Ticket.builder().ticketType(classify(x)).build())
                .collect(Collectors.groupingBy(Ticket::getTicketType));

        log.info("Categorising the request completed.Progressing further , transactionId:{} , ticketSize : {}",customerRequest.getTransactionId(),ticketMap.size());

        final BigDecimal[] totalCost = {new BigDecimal(0)};

        List<Ticket> pricedTicket = new ArrayList<>();

         log.info("Processing the request further for pricing , transactionId:{}",customerRequest.getTransactionId());
         ticketMap.forEach((category, value) -> {
                    if (isFamily.test(ticketMap, category)) {
                        totalCost[0] = pricingFactory.findPricingService(TicketType.FAMILY)
                                .calculatePrice(category, ticketMap, totalCost[0], pricedTicket);
                    } else {
                        totalCost[0] = pricingFactory.findPricingService(category)
                                .calculatePrice(category, ticketMap, totalCost[0], pricedTicket);

                    }
                }
        );


        return CustomerResponse.builder()
                .tickets(pricedTicket)
                .totalCost(totalCost[0])
                .transactionId(customerRequest.getTransactionId())
                .build();


    }


    private TicketType classify(Customer customer) {
        return Match(customer.getAge()).of(
                Case($(value ->CHILD.getRange().isValidIntValue(value)), () -> TicketType.CHILD),
                Case($(value ->TEEN.getRange().isValidIntValue(value)), () -> TicketType.TEEN),
                Case($(value ->ADULT.getRange().isValidIntValue(value)), () -> TicketType.ADULT),
                Case($(), TicketType.SENIOR));

    }

}
