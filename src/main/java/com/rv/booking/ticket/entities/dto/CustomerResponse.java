package com.rv.booking.ticket.entities.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CustomerResponse {
    Integer transactionId;

    List<Ticket> tickets;

    BigDecimal totalCost;
}
