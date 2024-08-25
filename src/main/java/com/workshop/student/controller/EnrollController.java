package com.workshop.student.controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.workshop.student.entity.StudentEntity;
import com.workshop.student.entity.CourseEntity;
import com.workshop.student.entity.EnrollEntity;
import com.workshop.student.service.CourseService;
import com.workshop.student.service.StudentService;
import com.workshop.student.service.EnrollService;  

@Controller
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollService enrollService;

    @GetMapping({"", "/"})
    public String getAll(ModelMap model) {
        System.out.println("-----EnrollController getAll()-----");

        // List<EnrollEntity> enrolls = enrollService.getEnrollAll();
        // System.out.println("-----EnrollController getAll() Resuil-----");
        // System.out.println("Size: " + enrolls.size());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);
        return "enroll/index";
    }

    @GetMapping("/{enroll-id}")
    public String getById(
        ModelMap model,
        @PathVariable(name = "student-id") Integer studentId
    ) {
        // System.out.println("-----EnrollController getById()-----");
        // System.out.println("enroll-id: " + enrollId);

        // EnrollEntity entity = enrollService.getEnrollById(enrollId);
        // System.out.println("-----EnrollController getId() Result-----");
        // System.out.println("Course Name: " + entity.getCourse().getCourseName());
        // System.out.println("Student Fist Name: " + entity.getStudent().getStudentFirstName());
        // System.out.println("Student Last Name: " + entity.getStudent().getStudentLastName());

        StudentEntity entity = studentService.getStudentById(studentId);
        model.addAttribute("student", entity);

        List<EnrollEntity> ebrolls = enrollService.getEnrollAll();
        List<EnrollEntity> resultList = enrolls.stream()
            .filter(enroll -> enroll.getStudent().getStudentId() == studentId)
            .collect(Collectors.toList());
        model.addAttribute("enrolls", resultList);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);
        
        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);
        return "enroll/index";
    }

    @GetMapping("/delete/{enroll-id}")
    public String getDeleteById(
        ModelMap model,
        @PathVariable(name = "enroll-id") Integer enrollId
    ) {
        // System.out.println("-----EnrollController getById()-----");
        // System.out.println("enroll-id: " + enrollId);

        // System.out.println("-----EnrollController getById() Result-----");
        enrollService.deleteEnrollById(enrollId);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);
        
        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);
        return "enroll/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        ModelMap model,
        @RequestParam() Map<String, String> param
    ) {
        // System.out.println("-----EnrollController postTutorialAndUpdate()-----");
        // System.out.println("enroll-id: " + param.get("enroll-id"));
        // System.out.println("enroll-name: " + param.get("enroll-name"));

        System.out.println("-----EnrollController postTutorialAndUpdate() Result-----");
        Integer courseId = Integer.parseInt(param.get("course-id"));
        CourseEntity courseEntity = courseService.getCourseById(courseId);
        // System.out.println("Course ID:" + courseEntity.getCourseId());

        Integer studentId = Integer.parseInt(param.get("student-id"));
        StudentEntity studentEntity = studentService.getStudentById(studentId);
        // System.out.println("Student ID:" + studentEntity.getStudentId());

        EnrollEntity entity = new EnrollEntity();
        if(null != param.get("enroll-id")) {
            entity.setEnrollId(Integer.parseInt(param.get("enroll-id")));
        }
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        EnrollEntity result = enrollService.saveEnroll(entity);
        // System.out.println("Enroll ID: " + result.getEnrollId());
        // System.out.println("Course Name: " + result.getCourse().getCourseName());
        // System.out.println("Student Name: " + result.getStudent().getStudentCode());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);
        
        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);
        return "enroll/index";
    }
    
}