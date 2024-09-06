package com.proxidevcode.spring_react_ecommerce.services.impl;

import com.proxidevcode.spring_react_ecommerce.dtos.CategoryResponse;
import com.proxidevcode.spring_react_ecommerce.dtos.ProductRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.ProductResponse;
import com.proxidevcode.spring_react_ecommerce.mappers.ProductMapper;
import com.proxidevcode.spring_react_ecommerce.models.Category;
import com.proxidevcode.spring_react_ecommerce.models.Product;
import com.proxidevcode.spring_react_ecommerce.repositories.CategoryRepository;
import com.proxidevcode.spring_react_ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;

    private Category category;
    private CategoryResponse categoryResponse;
    private Product product;
    private ProductResponse productResponse;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category(1L, "Category");
        categoryResponse = new CategoryResponse(1L, "Category");
        productRequest = ProductRequest.builder()
                .name("Product Name")
                .description("Product Description")
                .categoryId(1L)
                .price(1223.0)
                .quantity(23)
                .build();
        productResponse = ProductResponse.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .category(categoryResponse)
                .price(1223.0)
                .quantity(23)
                .build();

        product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .category(category)
                .price(1223.0)
                .quantity(23)
                .build();
    }

    @Test
    public void test_it_should_create_product_with_valid_data_and_returnProductResponse() {
        // Mock calls
        when(categoryRepository.findById(productRequest.getCategoryId())).thenReturn(Optional.of(category));
        try(MockedStatic<ProductMapper> mockedStatic = Mockito.mockStatic(ProductMapper.class)) {
            mockedStatic.when(() -> ProductMapper.mapToEntity(productRequest, category)).thenReturn(product);
            mockedStatic.when(() -> ProductMapper.mapToDto(product)).thenReturn(productResponse);
            when(productRepository.save(product)).thenReturn(product);

            // Act
            ProductResponse response = productService.createProduct(productRequest);

            // Assert
            assertNotNull(response);
            assertEquals(productRequest.getName(), response.getName());
            assertEquals(productRequest.getDescription(), response.getDescription());
            assertEquals(productRequest.getPrice(), response.getPrice());
            assertEquals(productRequest.getQuantity(), response.getQuantity());
            assertEquals(productRequest.getCategoryId(), response.getCategory().id());

            verify(productRepository, times(1)).save(product);
            verify(categoryRepository, times(1)).findById(1L);

        }


    }

}