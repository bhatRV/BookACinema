package com.rv.booking.ticket.repository;

import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discounts, Long> {

      Discounts findByTicketType(TicketType ticketType);

}