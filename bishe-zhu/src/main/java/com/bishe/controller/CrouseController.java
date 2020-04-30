package com.bishe.controller;


import com.bishe.entity.Course;
import com.bishe.entity.StudentCourse;
import com.bishe.entity.User;
import com.bishe.service.CourseService;
import com.bishe.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class CrouseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @RequestMapping("/member/{id}")
    public String memberlist(@PathVariable("id")Integer id, Model model){
        List<Course> courses = this.courseService.selectteachertask(id);
        if (CollectionUtils.isEmpty(courses)){
            return "error/error";
        }else {
            model.addAttribute("course",courses);
            return "member/member-list";

        }
    }


    @RequestMapping("/memberadd/{classes}")
    public String courselist(@PathVariable("classes")String classes,Model model){
        List<StudentCourse> courses = this.studentCourseService.selectCrouseNameLesson(classes);
        if (CollectionUtils.isEmpty(courses)){
            return "error/error";
        }else {
        model.addAttribute("subject",courses);
        return "member/member-add";
    }
  }
    @RequestMapping("/addcour")
    public String addcourse(
            String s_classes,
            String c_name,
            String c_time,
            Integer num,
            HttpServletRequest request,
            HttpServletResponse resp) throws IOException {
        if (StringUtils.isEmpty(s_classes)&&StringUtils.isEmpty(c_name)&&StringUtils.isEmpty(c_time)&&StringUtils.isEmpty(num)){
            return "error/error";
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        c_time=c_time.replace(",","，");
        this.studentCourseService.addcourse(user.getId(),c_name,c_time,s_classes,num);
        return "redirect:http://localhost:8080/member/"+user.getId();
    }
    /*删除所选的课程*/
    @RequestMapping("/delete")
    public @ResponseBody Integer deletecou(@RequestBody String value){
        Integer integer = this.courseService.deleteCourse(value);
        return integer;
    }
}