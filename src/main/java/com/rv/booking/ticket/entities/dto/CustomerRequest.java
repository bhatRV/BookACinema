package com.rv.booking.ticket.entities.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.List;


@Builder
public class CustomerRequest {
    Integer transactionId;
    @NotNull(message = "Please include atleast one ticket request")
    List<Customer> customers;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
