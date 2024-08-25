package com.workshop.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.student.entity.FacultyEntity;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Integer>{
    
}
