package com.example.shopapp.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    @JsonProperty("order_id")
    @Min(value = 0, message = "Order's id must be greater than or equal to 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 0, message = "Product's id must be greater than or equal to 0")
    private Long productId;

    private Float price;

    @JsonProperty("number_of_products")
    private Long numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    private String type;
}
