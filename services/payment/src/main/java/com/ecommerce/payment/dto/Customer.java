package com.ecommerce.payment.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String  id,
        @NotNull(message = "firstName is Required")
        String firstName,
        @NotNull(message = "lastName is Required")
        String lastName,
        @NotNull(message = "email is Required")
        @Email(message = "email is not valid")
        String email
) {

}
