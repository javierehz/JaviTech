package com.javieh.javitech.repository;

import com.javieh.javitech.entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {
    List<OrderAddress> findByOrderId(Long orderId);
}