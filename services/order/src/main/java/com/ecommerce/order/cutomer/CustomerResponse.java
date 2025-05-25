package com.ecommerce.order.cutomer;

public record CustomerResponse(

        String id,
        String firstName,
        String lastName,
        String email
) {
}
