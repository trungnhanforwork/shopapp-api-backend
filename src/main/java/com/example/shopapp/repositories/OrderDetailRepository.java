package com.example.shopapp.repositories;

import com.example.shopapp.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail findByOrderId(Long orderId);
}
