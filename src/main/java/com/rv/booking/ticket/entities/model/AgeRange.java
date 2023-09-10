package com.rv.booking.ticket.entities.model;

import java.time.temporal.ValueRange;
import java.util.function.Supplier;


public enum AgeRange {
    CHILD(() -> ValueRange.of(0, 11)),
    TEEN(() -> ValueRange.of(11, 17)),
    ADULT(() -> ValueRange.of(18, 65));

    private Supplier<ValueRange> rangeFactory = null;

    private AgeRange(Supplier<ValueRange> rangeFactory) {
        this.rangeFactory = rangeFactory;
    }

    public ValueRange getRange() {
        return rangeFactory.get();
    }

    String category;


}
