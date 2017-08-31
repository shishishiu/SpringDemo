package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{

    public DepartmentEntity findById(int id);
    public DepartmentEntity findByName(String name);
    public List<DepartmentEntity> findByEnableOrderByName(int enbale);

}
