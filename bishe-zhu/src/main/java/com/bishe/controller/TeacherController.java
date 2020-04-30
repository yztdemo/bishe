package com.bishe.controller;


import com.bishe.entity.Course;
import com.bishe.entity.Student;
import com.bishe.entity.StudentCourse;
import com.bishe.service.CourseService;
import com.bishe.service.StudentCourseService;
import com.bishe.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    //  查询老师 教的课程所带的班级
    @RequestMapping("/tclasses/{coursename}")
    public String gettclasses(@PathVariable("coursename")String coursename, Model model){

        if (StringUtils.isBlank(coursename)){
            return "error/error";
        }
        List<StudentCourse> classes = this.courseService.selectTeacherCC(coursename);
//        for (StudentCourse aClass : classes) {
//            System.out.println(aClass);
//        }
        model.addAttribute("studentcous",classes);
        return "member/member-edit";
    }

    // 更新 班级的上课时间 通过
    @RequestMapping("/updataTC")
    public @ResponseBody StudentCourse updataTeacherCourse(
            @RequestParam String banji,
            @RequestParam String week,
            @RequestParam String time,
            @RequestParam String cname){
        String str=week+"，"+time;
        StudentCourse studentCourse = this.studentCourseService.selectByBanJiAndTime(banji,str);
        if (studentCourse!=null){
            return studentCourse;
        }else {
          this.studentCourseService.updatateacherclass(banji,str,cname);
          return null;
        }
    }
}
