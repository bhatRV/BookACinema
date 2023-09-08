package com.rv.booking.ticket.repository;

import com.rv.booking.ticket.entities.model.PriceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceDetails, Long> {

}