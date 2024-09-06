package com.proxidevcode.spring_react_ecommerce.services.impl;


import com.proxidevcode.spring_react_ecommerce.dtos.PagedResponse;
import com.proxidevcode.spring_react_ecommerce.exceptions.ResourceNotFoundException;
import com.proxidevcode.spring_react_ecommerce.utils.AppConstants;
import jakarta.persistence.Id;
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

import java.util.Collections;
import java.util.List;

import static com.proxidevcode.spring_react_ecommerce.utils.AppConstants.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PagedResponse<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
       Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent().isEmpty() ?
                Collections.emptyList() : productPage.getContent();

       return PagedResponse.<ProductResponse>builder()
               .last(productPage.isLast())
               .first(productPage.isFirst())
               .content(products.stream().map(ProductMapper::mapToDto).toList())
               .totalPages(productPage.getTotalPages())
               .build();
    }

    @Override
    public ProductResponse createProduct(ProductRequest dto) {
        Category category  = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, dto.getCategoryId()));
        Product product = ProductMapper.mapToEntity(dto, category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToDto(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(long id, ProductRequest dto) {
       Product existingProduct = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException(PRODUCT, ID, id ));
       existingProduct.setName(dto.getName());
       existingProduct.setDescription(dto.getDescription());
       existingProduct.setPrice(dto.getPrice());
       existingProduct.setQuantity(dto.getQuantity());
       Product savedProduct = productRepository.save(existingProduct);
       return ProductMapper.mapToDto(savedProduct);
    }

    @Override
    public void productDelete(long id) {
       Product product = productRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException(PRODUCT, ID, id ));
       productRepository.delete(product);
    }

    @Override
    public ProductResponse getProduct(long id) {
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(PRODUCT, ID, id ));
        return ProductMapper.mapToDto(product);
    }

    @Override
    public PagedResponse<ProductResponse> getProductsByCategory(long id, int page, int size) {
        Category category  = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id ));
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        Page<Product> productPage = productRepository.findProductByCategory_Id(id, pageable);
        List<Product> products = productPage.getContent().isEmpty() ?
                Collections.emptyList() : productPage.getContent();
        return PagedResponse.<ProductResponse>builder()
                .last(productPage.isLast())
                .first(productPage.isFirst())
                .content(products.stream().map(ProductMapper::mapToDto).toList())
                .totalPages(productPage.getTotalPages())
                .build();
    }
    
}
