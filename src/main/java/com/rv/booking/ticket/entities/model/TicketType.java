package com.rv.booking.ticket.entities.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.function.Supplier;
import java.util.stream.IntStream;


public enum TicketType {
    ADULT("ADULT"),
    SENIOR("SENIOR"),
    CHILD("CHILD");

    String category;

    TicketType(String type) {
    }
}
