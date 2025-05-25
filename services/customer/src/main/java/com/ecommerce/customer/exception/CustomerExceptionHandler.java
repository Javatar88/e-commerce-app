package com.ecommerce.customer.exception;

import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class CustomerExceptionHandler {



    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String>  CustomerException(CustomerNotFoundException exp){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
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
