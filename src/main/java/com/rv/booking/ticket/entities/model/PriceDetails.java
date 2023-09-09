package com.rv.booking.ticket.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "Price_Details")
public class PriceDetails {
    @Id
    private Long id;

    @Column(name = "MOVIE_TYPE")
    String movieType;
    @Column(name = "price")
    BigDecimal price;

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
