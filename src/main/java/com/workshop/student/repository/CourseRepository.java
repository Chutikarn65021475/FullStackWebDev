package com.workshop.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.student.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer>{
    
}
