package com.example.demo.repository;


import com.example.demo.entity.NumberPoolGroupRela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NumberPoolGroupRelaRepository extends JpaRepository<NumberPoolGroupRela, String> {

    List<NumberPoolGroupRela> findByNumbergroupid(String groupid);

}
