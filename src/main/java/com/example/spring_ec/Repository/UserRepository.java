package com.example.spring_ec.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ec.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByEmail(String email);
}
