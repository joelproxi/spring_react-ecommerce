package com.proxidevcode.spring_react_ecommerce.mappers;

import com.proxidevcode.spring_react_ecommerce.dtos.CategoryResponse;
import com.proxidevcode.spring_react_ecommerce.dtos.ProductRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.ProductResponse;
import com.proxidevcode.spring_react_ecommerce.models.Category;
import com.proxidevcode.spring_react_ecommerce.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private ProductResponse productResponse;
    private ProductRequest productRequest;
    private Product product;
    private Category category;
    private CategoryResponse categoryResponse;

    @BeforeEach
    void setUp() {
        category = new Category(1L, "Iphone");
        categoryResponse = new CategoryResponse(1L, "Iphone");

        productResponse =  ProductResponse.builder()
                .id(1L)
                .name("Ipad")
                .description("Ipad description")
                .price(100.00)
                .quantity(1)
                .category(categoryResponse)
                .build();


        product = Product.builder()
                .id(1L)
                .name("Ipad")
                .description("Ipad description")
                .price(100.00)
                .quantity(1)
                .category(category)
                .build();

        productRequest =  ProductRequest.builder()
                .name("Ipad")
                .description("Ipad description")
                .price(100.00)
                .quantity(1)
                .categoryId(1L)
                .build();


    }

    @Test
    public void test_it_should_map_productRequest_to_product() {
        // Arrange && Act
        Product result = ProductMapper.mapToEntity(productRequest, category);

        //Assert
        assertNotNull(result);
        assertEquals(productRequest.getName(), result.getName());
        assertEquals(productRequest.getDescription(), result.getDescription());
        assertEquals(productRequest.getPrice(), result.getPrice());
        assertEquals(productRequest.getQuantity(), result.getQuantity());

    }

    @Test
    public void test_it_should_map_product_to_productResponse() {
        // Arrange && Act
        ProductResponse result = ProductMapper.mapToDto(product);

        //Assert
        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
    }

    @Test
    public void test_it_should_not_map_product_to_categoryResponse_and_throw_NullPointerException() {
        assertThrows(NullPointerException.class, () -> ProductMapper.mapToDto(null));

    }
}