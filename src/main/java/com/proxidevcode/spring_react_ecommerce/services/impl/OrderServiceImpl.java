package com.proxidevcode.spring_react_ecommerce.services.impl;

import com.proxidevcode.spring_react_ecommerce.dtos.OrderRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.OrderResponse;
import com.proxidevcode.spring_react_ecommerce.dtos.PagedResponse;
import com.proxidevcode.spring_react_ecommerce.mappers.OrderMapper;
import com.proxidevcode.spring_react_ecommerce.models.Order;
import com.proxidevcode.spring_react_ecommerce.models.OrderProduct;
import com.proxidevcode.spring_react_ecommerce.models.Product;
import com.proxidevcode.spring_react_ecommerce.repositories.OrderProductRepository;
import com.proxidevcode.spring_react_ecommerce.repositories.OrderRepository;
import com.proxidevcode.spring_react_ecommerce.repositories.ProductRepository;
import com.proxidevcode.spring_react_ecommerce.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;


    @Override
    public PagedResponse<OrderResponse> getAllOrders(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "firstName"));
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return PagedResponse.<OrderResponse>builder()
                .content(orderPage.getContent().stream().map(orderMapper::mapToDto).toList())
                .last(orderPage.isLast())
                .first(orderPage.isFirst())
                .totalPages(orderPage.getTotalPages())
                .totalElements(orderPage.getTotalElements())
                .number(orderPage.getNumber())
                .size(orderPage.getSize())
                .build();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    @Override
    public OrderResponse createOrder(OrderRequest entity) {
        if(entity == null) {
           throw new IllegalArgumentException("OrderRequest is null");
        }
        if (entity.getOrderProducts().isEmpty()){
            throw new IllegalArgumentException("OrderProducts is empty");
        }

        Set<OrderProduct> orderProducts = entity.getOrderProducts().stream().map(
                orderProductRequest -> {
                    Product product = productRepository.findById(orderProductRequest.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found: "+ orderProductRequest.getProductId()));
                    if(product.getQuantity() < orderProductRequest.getQuantity()) {
                        throw new IllegalArgumentException("OrderProducts contains insufficient quantity");
                    }

                    product.setQuantity(product.getQuantity() - orderProductRequest.getQuantity());
                    productRepository.save(product);

                    return OrderProduct.builder()
                            .quantity(orderProductRequest.getQuantity())
                            .price(orderProductRequest.getPrice())
                            .product(product)
                            .build();
                }
        ).collect(Collectors.toSet());

        Order order = Order.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .orderProducts(orderProducts)
                .build();

        orderProducts.forEach(orderProduct -> orderProduct.setOrder(order));

        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapToDto(savedOrder);

    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest, String id) {
        return null;
    }

    @Override
    public OrderResponse deleteOrder(String id) {
        return null;
    }
}
