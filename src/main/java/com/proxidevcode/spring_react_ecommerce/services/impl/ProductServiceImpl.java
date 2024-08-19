package com.proxidevcode.spring_react_ecommerce.services.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proxidevcode.spring_react_ecommerce.dtos.ProductRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.ProductResponse;
import com.proxidevcode.spring_react_ecommerce.mappers.ProductMapper;
import com.proxidevcode.spring_react_ecommerce.models.Category;
import com.proxidevcode.spring_react_ecommerce.models.Product;
import com.proxidevcode.spring_react_ecommerce.repositories.CategoryRepository;
import com.proxidevcode.spring_react_ecommerce.repositories.ProductRepository;
import com.proxidevcode.spring_react_ecommerce.services.ProductService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
       Page<Product> products = productRepository.findAll(pageable);
       return products.map(ProductMapper::mapToDto);
    }

    @Override
    public ProductResponse createProduct(ProductRequest dto) {
        Category category  = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Product product = ProductMapper.mapToEntity(dto, category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToDto(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(long id, ProductRequest dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void productDelete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'productDelete'");
    }

    @Override
    public ProductResponse getProduct(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProduct'");
    }

    @Override
    public Page<ProductResponse> getProductsByCategory(long id, int page, int size) {
        Category category  = categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC);
        Page<Product> products = productRepository.findProductByCategory_Id(id, pageable);
        return products.map(ProductMapper::mapToDto);
    }
    
}
