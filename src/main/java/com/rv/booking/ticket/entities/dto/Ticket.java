package com.rv.booking.ticket.entities.dto;

import com.rv.booking.ticket.entities.model.TicketType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Ticket {
    TicketType ticketType;
    Integer quantity;
    BigDecimal cost;

}
