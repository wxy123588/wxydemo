package com.example.demo.repository;

import com.example.demo.entity.NumberPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NumberPoolRepository extends JpaRepository<NumberPool, String> {

    public NumberPool findById(String id) ;

    @Query(value = "select u.* from uk_number_pool u left join uk_number_pool_group_rela unr on u.id=unr.numberpoolid left join uk_number_pool_group unp on unr.numbergroupid=unp.id where unp.id=?1 and u.province=?2 and u.city=?3"
            ,nativeQuery = true)
    List<NumberPool> findBygroupidandprovinceAndcity(String groupid, String province, String city);

    @Query(value = "select u.* from uk_number_pool u left join uk_number_pool_group_rela unr on u.id=unr.numberpoolid left join uk_number_pool_group unp on unr.numbergroupid=unp.id where unp.id=?1 and u.province=?2"
            ,nativeQuery = true)
    List<NumberPool> findBygroupidandprovince(String groupid, String privince);

    @Query(value = "select u.* from uk_number_pool u left join uk_number_pool_group_rela unr on u.id=unr.numberpoolid left join uk_number_pool_group unp on unr.numbergroupid=unp.id where unp.id=?1 "
            ,nativeQuery = true)
    List<NumberPool> findBygroupid(String groupid);

    @Query(value = "select u.* from uk_number_pool u left join uk_number_pool_group_rela unr on u.id=unr.numberpoolid left join uk_number_pool_group unp on unr.numbergroupid=unp.id where unp.id=?1 and u.province=?2 and u.city=?3"
            ,nativeQuery = true)
    List<NumberPool> findBygroupidandprovinceAndcity1(String groupid, String province, String city);

    @Query(value = "select u.* from uk_number_pool u left join uk_number_pool_group_rela unr on u.id=unr.numberpoolid left join uk_number_pool_group unp on unr.numbergroupid=unp.id where unp.id=?1  order by u.count asc limit 1"
            ,nativeQuery = true)
    List<NumberPool> findBygroupidmaxcount(String groupid);



}
