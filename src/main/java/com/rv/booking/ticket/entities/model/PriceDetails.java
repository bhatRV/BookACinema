package com.rv.booking.ticket.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


/**
 * @author :Rashmi
 * This is the Entity object for Storing PriceDetails
 */
@JsonIgnoreProperties
@Entity
@Table(name = "PriceDetails")
public class PriceDetails {
    @Id
    private Long id;

    @Column(name = "movieType")
    String movieType;
    @Column(name = "price")
    BigDecimal price;
}
