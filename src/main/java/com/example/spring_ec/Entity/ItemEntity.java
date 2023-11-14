package com.example.spring_ec.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class ItemEntity {
    @Id
    @Column(name="code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    
    @Column(name="name")
    private String name;

    @Column(name="price")
    private Integer price;

    @Column(name="stock")
    private Integer stock;

    @Column(name="category_code")
    private Integer categoryCode;

    public ItemEntity(){}

    public ItemEntity(String name, Integer price, Integer stock, Integer categoryCode){
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryCode = categoryCode;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public Integer getPrice(){
        return price;
    }

    public Integer getStock(){
        return stock;
    }

    public void setStock(Integer stock){
        this.stock = stock;
    }

    public Integer getCategoryCode(){
        return categoryCode;
    }
}
