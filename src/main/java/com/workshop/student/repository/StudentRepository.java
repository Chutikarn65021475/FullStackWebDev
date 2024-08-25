package com.workshop.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{
    
}
