package com.example.spring_ec.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @Column(name="code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    
    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    public UserEntity(){}

    public UserEntity(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
