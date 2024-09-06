package com.proxidevcode.spring_react_ecommerce.repositories;

import com.proxidevcode.spring_react_ecommerce.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
