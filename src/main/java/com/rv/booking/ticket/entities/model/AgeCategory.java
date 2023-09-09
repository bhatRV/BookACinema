package com.rv.booking.ticket.entities.model;

import java.time.temporal.ValueRange;
import java.util.function.Supplier;
import java.util.stream.IntStream;


public enum AgeCategory {
    CHILD(() -> ValueRange.of(0, 11)),
    TEEN(() -> ValueRange.of(11, 17)),
    ADULT(() -> ValueRange.of(18, 65));

    private Supplier<ValueRange> rangeFactory = null;

    private AgeCategory(Supplier<ValueRange> rangeFactory) {
        this.rangeFactory = rangeFactory;
    }

    public ValueRange getRange() {
        return rangeFactory.get();
    }

    String category;


}
