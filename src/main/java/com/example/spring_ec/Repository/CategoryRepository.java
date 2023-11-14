package com.example.spring_ec.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ec.Entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    
}
