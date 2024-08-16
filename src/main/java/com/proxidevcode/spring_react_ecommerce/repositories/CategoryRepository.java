package com.proxidevcode.spring_react_ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxidevcode.spring_react_ecommerce.models.Category;

/**
 * CategoryRepository
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{

    
}