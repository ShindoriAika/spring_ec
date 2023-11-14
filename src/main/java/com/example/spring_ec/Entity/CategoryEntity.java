package com.example.spring_ec.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
public class CategoryEntity {
    @Id
    @Column(name="code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    
    @Column(name="name")
    private String name;

    public CategoryEntity(){}

    public CategoryEntity(String name){
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
