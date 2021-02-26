package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public abstract interface UserRepository extends JpaRepository<User, String>
{
    public abstract User findById(String id);

}
