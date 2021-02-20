package com.example.demo.repository;

import com.example.demo.entity.StatusEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusEventRepository extends JpaRepository<StatusEvent, String> {

    public StatusEvent findById(String id);

    public List<StatusEvent> findByCalledAndAnswer(String called, String answer);

    //查询最近10天数据
    @Query(value = "SELECT * FROM uk_callcenter_event where 1=1 and answer='answered' and DATEDIFF(SYSDATE(),createtime)<10",nativeQuery = true)
    public List<StatusEvent> findbycreatetime() ;

}
