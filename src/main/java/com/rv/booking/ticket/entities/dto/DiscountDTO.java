package com.rv.booking.ticket.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rv.booking.ticket.entities.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {

    @JsonProperty("ticketType")
    private TicketType ticketType;

    @JsonProperty("discount")
    private BigDecimal discount;

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}