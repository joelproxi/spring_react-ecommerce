package com.proxidevcode.spring_react_ecommerce.controllers;

import static com.proxidevcode.spring_react_ecommerce.utils.AppConstants.PAGE_NUMBER;
import static com.proxidevcode.spring_react_ecommerce.utils.AppConstants.PAGE_SIZE;

import com.proxidevcode.spring_react_ecommerce.dtos.PagedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proxidevcode.spring_react_ecommerce.dtos.ProductRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.ProductResponse;
import com.proxidevcode.spring_react_ecommerce.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PagedResponse<ProductResponse>> getAllProducts(
        @RequestParam(name = "page", required = false, defaultValue = PAGE_NUMBER) int page, 
        @RequestParam(name = "size", required = false, defaultValue = PAGE_SIZE) int size
    ){
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
    
    @GetMapping("/category/{id}")
    public ResponseEntity<PagedResponse<ProductResponse>> getProductByCategory(
            @PathVariable(value = "id") int id,
            @RequestParam(name = "page", required = false, defaultValue = PAGE_NUMBER) int page,
            @RequestParam(name = "size", required = false, defaultValue = PAGE_SIZE) int size){
        return ResponseEntity.ok(productService.getProductsByCategory(id, page, size));
    }

}
