package com.example.demo.repository;

import com.example.demo.entity.StatusEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusEventRepository extends JpaRepository<StatusEvent, String> {

    public StatusEvent findById(String id);

    public List<StatusEvent> findByCalledAndAnswer(String called, String answer);

}
