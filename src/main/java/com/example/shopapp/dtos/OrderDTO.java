package com.example.shopapp.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @Min(value = 1, message = "User id must be greater than or equal to 1.")
    @JsonProperty("user_id")
    private Long userId;

    @NotBlank(message = "Full name cannot be empty.")
    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @NotBlank(message = "Phone number cannot be empty.")
    @Size(min = 10, message = "Invalid phone number.")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be empty.")
    private String address;

    private String note;

    @Min(value = 0, message = "Total money must be greater than or equal to 0.")
    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;


    @JsonProperty("payment_method")
    private String paymentMethod;

}
