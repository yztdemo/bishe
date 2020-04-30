package com.bishe.controller;


import com.bishe.entity.CourseExt;
import com.bishe.entity.StudentCourse;
import com.bishe.service.CourseService;
import com.bishe.service.StudentCourseService;
import com.bishe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("student/{id}")
    public String getStudentClasses(@PathVariable("id")Integer id, Model model){
        List<StudentCourse> courses = this.studentService.selectCourseById(id);
        List<CourseExt> courseExts = this.courseService.selectCourseExt();
        model.addAttribute("course",courses);
        model.addAttribute("courseExts",courseExts);
        return "studentcourse";
    }
}
