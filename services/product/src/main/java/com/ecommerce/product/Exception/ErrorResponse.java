package com.ecommerce.product.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
