package com.rv.booking.ticket.entities.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Customer {
    @NotNull(message = "Please provide first Name")
    @NotEmpty(message = "Please provide first Name")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-]*$", message = "first name must be alphanumeric and start with character")
    String name;


    @NotNull(message = "Please provide the age")
    @Digits(integer = 120, fraction = 0, message = "age should be number and no larger than 120")
    Integer age;
}
