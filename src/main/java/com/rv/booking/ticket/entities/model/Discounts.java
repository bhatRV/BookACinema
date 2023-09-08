package com.rv.booking.ticket.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "ageCategory")
    private AgeCategory ageCategory;
    @Column(name = "discount")
    private BigDecimal discount;
}

