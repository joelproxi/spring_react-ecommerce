package com.proxidevcode.spring_react_ecommerce.services;

import java.util.List;

import com.proxidevcode.spring_react_ecommerce.dtos.PagedResponse;
import org.springframework.data.domain.Page;

import com.proxidevcode.spring_react_ecommerce.dtos.ProductRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.ProductResponse;

public interface ProductService {
    
    PagedResponse<ProductResponse> getAllProducts(int page, int size);

    ProductResponse createProduct(ProductRequest dto);

    ProductResponse updateProduct(long id, ProductRequest dto);

    void productDelete(long id);

    ProductResponse getProduct(long id);

    PagedResponse<ProductResponse> getProductsByCategory(long id,int page, int size);
}
