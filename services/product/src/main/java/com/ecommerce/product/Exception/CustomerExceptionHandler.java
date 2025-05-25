package com.ecommerce.product.Exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class CustomerExceptionHandler {



    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String>  CustomerException(ProductPurchaseException exp){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String>  CustomerException(EntityNotFoundException exp){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>  CustomerException(MethodArgumentNotValidException exp){
        var errors= new HashMap<String,String>();
        exp.getBindingResult().getAllErrors().forEach(
                error->{
                    var  field= ((FieldError)error).getField();
                    var message= error.getDefaultMessage();
                    errors.put(field, message);
                }
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

}
