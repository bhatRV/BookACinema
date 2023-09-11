package com.rv.booking.ticket.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rv.booking.ticket.entities.model.TicketType;

import java.math.BigDecimal;


public class PriceDTO {

    @JsonProperty("ticketType")
    private TicketType ticketType;

    @JsonProperty("price")
    private BigDecimal price;

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}