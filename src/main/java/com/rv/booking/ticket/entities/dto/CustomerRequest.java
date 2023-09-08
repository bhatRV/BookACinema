package com.rv.booking.ticket.entities.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CustomerRequest {
    Integer transactionId;
    List<Customer> customers;
}
