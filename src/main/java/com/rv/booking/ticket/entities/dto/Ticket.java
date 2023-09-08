package com.rv.booking.ticket.entities.dto;

import com.rv.booking.ticket.entities.model.TicketType;
import lombok.Data;

@Data
public class Ticket {
    TicketType ticketType;
    Integer quantity;
    Double cost;

}
