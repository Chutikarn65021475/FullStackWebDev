package com.workshop.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.student.entity.EnrollEntity;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Integer>{
    
}
