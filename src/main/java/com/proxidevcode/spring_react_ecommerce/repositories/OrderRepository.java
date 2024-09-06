package com.proxidevcode.spring_react_ecommerce.repositories;

import com.proxidevcode.spring_react_ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
