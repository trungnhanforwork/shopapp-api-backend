package com.example.shopapp.controllers;


import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderDetailById(
            @Valid @PathVariable Long id
    ) {
            return ResponseEntity.status(HttpStatus.OK).body("Get order detail by id: " +id);
    }

    @GetMapping("order/{order_id}")
    public ResponseEntity<?> getOrderDetailByOrderId(
            @Valid @PathVariable("order_id") Long orderId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("Get list order detail with order id: " + orderId);
    }

    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
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

            return ResponseEntity.status(HttpStatus.OK).body("Create order detail successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable Long id,
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
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

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Update order detail successfully with id: " +id + ", new OderDetail: " +orderDetailDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
