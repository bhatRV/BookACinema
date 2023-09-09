package com.rv.booking.ticket.service;


import com.rv.booking.ticket.entities.dto.Ticket;
import com.rv.booking.ticket.entities.model.TicketType;

public interface PricingService {

    public Ticket calculatePrice(TicketType type, int count);

    public boolean supportType(TicketType type);
}
