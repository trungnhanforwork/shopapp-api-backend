package com.example.shopapp.controllers;


import com.example.shopapp.dtos.UserDTO;
import com.example.shopapp.dtos.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            }
            if (!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is not matched.");
            }
            return  ResponseEntity.status(HttpStatus.OK).body("Create user successfully!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO

    ) {
        // Login successfully, return a token into response
       return ResponseEntity.status(HttpStatus.OK).body("Some token ");
    }
}
