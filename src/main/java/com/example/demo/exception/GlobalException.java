package com.example.demo.exception;

import com.example.demo.entity.Order;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException{

   @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<?> orderNotFound(OrderNotFound orderNotFound){
       Map<String, String >map = new HashMap<>();
       map.put("message",orderNotFound.getMessage());
       return ResponseEntity.badRequest().body(map);
   }
    @ExceptionHandler(OrderDateNotFound.class)
    public ResponseEntity<?> orderDateNotFound(OrderDateNotFound orderDateNotFound){
        Map<String, String >map = new HashMap<>();
        map.put("message",orderDateNotFound.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String >map = new HashMap<>();
        ex.getAllErrors().forEach(objectError -> {
            String field = ((FieldError) objectError).getField();
            String defaultMessage = objectError.getDefaultMessage();
            map.put(field,defaultMessage);

        });
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> allException(Exception ex){
        Map<String, String >map = new HashMap<>();
        map.put("message",ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}
