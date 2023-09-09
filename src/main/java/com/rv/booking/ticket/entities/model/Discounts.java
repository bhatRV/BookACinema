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
 * This is the Entity object for Storing Discounts
 */
@JsonIgnoreProperties
@Entity
@Table(name = "Discounts")
public class Discounts {
    @Id
    private Long id;
    @Column(name = "AGE_CATEGORY")
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column(name = "discount")
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

