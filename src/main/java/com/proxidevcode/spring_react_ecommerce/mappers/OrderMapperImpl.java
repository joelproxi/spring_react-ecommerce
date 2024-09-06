package com.proxidevcode.spring_react_ecommerce.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import com.proxidevcode.spring_react_ecommerce.dtos.OrderProductResponse;
import com.proxidevcode.spring_react_ecommerce.dtos.OrderRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.OrderResponse;
import com.proxidevcode.spring_react_ecommerce.models.Order;
import com.proxidevcode.spring_react_ecommerce.models.OrderProduct;
import org.springframework.stereotype.Service;

@Service
public class OrderMapperImpl implements OrderMapper{

   

    @Override
    public OrderResponse mapToDto(Order entity) {

        Set<OrderProductResponse> orderProducts = entity.getOrderProducts().stream().map(
                OrderProductMapper::mapToDto
        ).collect(Collectors.toSet());

      return OrderResponse.builder()
      .id(entity.getId())
      .firstName(entity.getFirstName())
      .lastName(entity.getLastName())
      .email(entity.getEmail())
      .address(entity.getAddress())
      .orderProducts(orderProducts)
      .build();
    }



    @Override
    public Order mapToEntity(OrderRequest dto, Set<OrderProduct> orderProducts) {
        return Order.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .orderProducts(orderProducts)
        .build();
    }
    
}
