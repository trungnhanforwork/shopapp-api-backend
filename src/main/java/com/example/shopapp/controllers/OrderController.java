package com.example.shopapp.controllers;


import com.example.shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody OrderDTO orderDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            }

            return ResponseEntity.status(HttpStatus.OK).body("Create order successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrder(@PathVariable("user_id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @PathVariable("id") Long id,
            @RequestBody OrderDTO orderDTO
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("Update order successfull with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("Delete order successfull with id: " + id);
    }
}
