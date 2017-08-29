package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.MemberEntity;


public interface MemberRepository extends JpaRepository<MemberEntity, Long>
{
    public MemberEntity findByLoginId(String loginId);
    
    @Query("select a from mst_user a where (a.username like %:keyword% or a.loginId like %:keyword%) and a.enabled=1 order by a.loginId")
    List<MemberEntity> findUsers(@Param("keyword") String keyword);


}