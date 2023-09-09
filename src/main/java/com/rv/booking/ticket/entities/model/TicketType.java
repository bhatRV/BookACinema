package com.rv.booking.ticket.entities.model;

public enum TicketType {
    ADULT("ADULT"),
    SENIOR("SENIOR"),
    TEEN("TEEN"),
    CHILD("CHILD"),
    FAMILY("FAMILY");

    String category;

    TicketType(String type) {
    }
}
