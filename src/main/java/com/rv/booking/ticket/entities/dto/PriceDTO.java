package com.rv.booking.ticket.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class PriceDTO {

    @JsonProperty("movieType")
    private String movieType;

    @JsonProperty("price")
    private BigDecimal price;

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