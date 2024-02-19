package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "fullname", length = 100, nullable = false)
    private String fullName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "note", length = 100)
    private String note;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_money", nullable = false)
    private Float totalMoney;

    @Column(name = "shipping_method", length = 100)
    private String shippingMethod;

    @Column(name = "shipping_address", length = 200, nullable = false)
    private String shippingAddress;

    @Column(name = "shipping_date", nullable = false)
    private LocalDateTime shippingDate;

    @Column(name = "tracking_number", length = 100, nullable = false)
    private String trackingNumber;

    @Column(name = "payment_method", length = 100)
    private String paymentMethod;

    private Boolean active;
}
