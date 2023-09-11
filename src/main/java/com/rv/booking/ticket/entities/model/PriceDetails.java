package com.rv.booking.ticket.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


/**
 * @author :Rashmi
 * This is the Entity object for Storing PriceDetails
 */
@JsonIgnoreProperties
@Entity
@Table(name = "Price_Details")
public class PriceDetails {
    @Id
    private Long id;
    @Column(name = "AGE_CATEGORY")
    @Enumerated(EnumType.STRING)
    TicketType ticketType;
    @Column(name = "price")
    BigDecimal price;

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

    public void setId(Long id) {
        this.id = id;
    }
}
