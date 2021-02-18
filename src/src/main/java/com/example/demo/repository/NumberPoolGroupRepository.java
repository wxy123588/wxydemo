package com.example.demo.repository;

import com.example.demo.entity.NumberPoolGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NumberPoolGroupRepository extends JpaRepository<NumberPoolGroup, String> {

    public NumberPoolGroup findById(String id);


}