package com.proxidevcode.spring_react_ecommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxidevcode.spring_react_ecommerce.dtos.CategoryRequest;
import com.proxidevcode.spring_react_ecommerce.dtos.CategoryResponse;
import com.proxidevcode.spring_react_ecommerce.mappers.CategoryMapper;
import com.proxidevcode.spring_react_ecommerce.models.Category;
import com.proxidevcode.spring_react_ecommerce.repositories.CategoryRepository;
import com.proxidevcode.spring_react_ecommerce.services.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Long id){
        Category category = categoryRepository.findById(id).get();
        return categoryMapper.mapToDto(category);
 
    }
    
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest dto){
        return new ResponseEntity<>(categoryService.createCategory(dto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id){
        Category existingCategory = categoryRepository.findById(id).get();
        existingCategory.setName(category.getName());
        Category savedcategory = categoryRepository.save(existingCategory);
        return  savedcategory;
    }

}
