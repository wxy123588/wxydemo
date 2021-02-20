package com.example.demo.repository;


import com.example.demo.entity.NumberPool;
import com.example.demo.entity.NumberPoolGroupRela;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NumberPoolGroupRelaRepository extends JpaRepository<NumberPoolGroupRela, String> {

    List<NumberPoolGroupRela> findByNumbergroupid(String groupid);
}
