package com.workshop.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.student.entity.CourseEntity;
import com.workshop.student.repository.CourseRepository;

@Service
public class CourseService {
    
@Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> getCourseAll() {
        return courseRepository.findAll();
    }

    public CourseEntity getCourseById(Integer courseId) {
        Optional<CourseEntity> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            return course.get();
        }
        return null;
    }

    public CourseEntity saveCourse(CourseEntity courseEntity) {
        CourseEntity course = courseRepository.save(courseEntity);
        return course;
    }

    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

}
