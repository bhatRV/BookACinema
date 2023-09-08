package com.rv.booking.ticket.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {
    Integer transactionId;
    List<Ticket> tickets;

    Double totalCost;
 }
