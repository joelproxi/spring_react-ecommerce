package com.proxidevcode.spring_react_ecommerce.controllers;

import com.proxidevcode.spring_react_ecommerce.dtos.OrderRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.OrderResponse;
import com.proxidevcode.spring_react_ecommerce.dtos.PagedResponse;
import com.proxidevcode.spring_react_ecommerce.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    ResponseEntity<PagedResponse<OrderResponse>> getAllOrders(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(orderService.getAllOrders(page, size));
    }

    @PostMapping
    ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }
}
