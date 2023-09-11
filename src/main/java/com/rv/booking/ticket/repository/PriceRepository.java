package com.rv.booking.ticket.repository;

import com.rv.booking.ticket.entities.model.PriceDetails;
import com.rv.booking.ticket.entities.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceDetails, Long> {
      PriceDetails findByTicketType(TicketType ticketType);

}