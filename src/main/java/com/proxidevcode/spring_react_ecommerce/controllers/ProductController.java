package com.proxidevcode.spring_react_ecommerce.controllers;

import static com.proxidevcode.spring_react_ecommerce.utils.AppConstants.PAGE_NUMBER;
import static com.proxidevcode.spring_react_ecommerce.utils.AppConstants.PAGE_SIZE;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proxidevcode.spring_react_ecommerce.dtos.ProductResponse;
import com.proxidevcode.spring_react_ecommerce.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
        @RequestParam(name = "page", required = false, defaultValue = PAGE_NUMBER) int page, 
        @RequestParam(name = "size", required = false, defaultValue = PAGE_SIZE) int size
    ){
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }
    
}
