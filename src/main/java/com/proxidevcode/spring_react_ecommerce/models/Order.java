package com.proxidevcode.spring_react_ecommerce.models;


import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private String address;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts;
    
}
