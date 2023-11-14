package com.example.spring_ec.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ec.Entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    List<ItemEntity> findByCategoryCode(Integer categoryCode);
    List<ItemEntity> findByNameLike(String name);
    List<ItemEntity> findByCategoryCodeAndNameLike(Integer categoryCode, String name);
    ItemEntity findByCode(Integer code);
}
