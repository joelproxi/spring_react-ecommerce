package com.proxidevcode.spring_react_ecommerce.mappers;

import com.proxidevcode.spring_react_ecommerce.dtos.CategoryRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.CategoryResponse;
import com.proxidevcode.spring_react_ecommerce.models.Category;
import org.assertj.core.api.Assert;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapper();
    }

    @Test
    public void it_should_map_categoryRequest_to_category(){
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest("iphone");

        // Act
        Category response = categoryMapper.mapToEntity(categoryRequest);

        // Assert
        assertNotNull(response);
        assertEquals("iphone", response.getName());
    }

    @Test
    public void it_should_map_category_to_categoryResponse(){
        // Arrange
        Category category = new Category(1L, "iphone");

        //Act
        CategoryResponse categoryResponse = categoryMapper.mapToDto(category);

        // Assert
        assertNotNull(categoryResponse);
        assertEquals(category.getId(), categoryResponse.id());
        assertEquals(category.getName(), categoryResponse.name());
    }

    @Test
    public void test_it_should_not_map_nullEntity_to_dto_and_throw_NullPointerException(){
        // Arrange, Act and Assert
        Category category = new Category(1L, "iphone");
        var mesg = assertThrows(NullPointerException.class, () -> categoryMapper.mapToDto(null));

        assertEquals("Entity not found", mesg.getMessage());
    }
}