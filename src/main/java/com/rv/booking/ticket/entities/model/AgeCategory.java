package com.rv.booking.ticket.entities.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;
import java.util.stream.IntStream;


public enum AgeCategory {

    CHILD( () -> IntStream.range(0, 11) ),
    TEEN( () -> IntStream.range(11, 17) ),
    ADULT( () -> IntStream.range(18, 65) ),
    SENIOR( () -> IntStream.range(65, 120) ),

    INVALID( () -> IntStream.range(120, Integer.MAX_VALUE) );


    private Supplier<IntStream> rangeFactory = null;

    private AgeCategory(Supplier<IntStream> rangeFactory){
        this.rangeFactory = rangeFactory;
    }

    public IntStream getRange() {
        return rangeFactory.get();
    }
    String category;


}
