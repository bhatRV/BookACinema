package com.rv.booking.ticket.service;


import com.rv.booking.ticket.entities.dto.Ticket;
import com.rv.booking.ticket.entities.model.TicketType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PricingService {

     BigDecimal calculatePrice(TicketType key, Map<TicketType, List<Ticket>> ticketMap, BigDecimal totalCost, List<Ticket> pricedTicket);

     boolean supportType(TicketType type);
}
