package com.bishe.controller;


import com.bishe.entity.Course;
import com.bishe.entity.Student;
import com.bishe.entity.Teacher;
import com.bishe.entity.User;
import com.bishe.service.CourseService;
import com.bishe.service.StudentService;
import com.bishe.service.TeacherService;
import com.bishe.service.UserService;
import com.bishe.untils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    /*普通的用户名密码登录*/
    @PostMapping("/login")
    public String loginsys(User user,HttpServletRequest request){
        Integer count;
        HttpSession session = request.getSession();
        User user1 = this.userService.selectuserbyname(user);
        //这学期你要教的科目有哪些  （老师）
        List<Course> courses = this.courseService.selectteachertask(user1.getId());
        // 这学期 教你实验课的老师有（学生）
        List<Teacher> teachers = this.teacherService.selecTeacherbyid(user1.getId());
        if (user1.getRole().equals("teacher")){
            // 这学期 一共要上几门实验课（老师 学生）
            count = this.teacherService.selectCourseCountC(user1.getId());
        }else {
            count = this.studentService.selectCourseCountC(user1.getId());
        }
        if (user1==null){
            return "error/error";
        }else{
            session.setAttribute("course",courses);
            session.setAttribute("count",count);
            session.setAttribute("teachername",teachers);
            session.setAttribute("user",user1);
            return "main";
        }
    }
    /*短信验证码进行登录*/
    @PostMapping("/login1")
    public String longinphone(@RequestParam String phonenumber,@RequestParam String verificationcode,HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer count;
        if (redisUtil.get("code").equals(verificationcode)){
            User user = new User();
            user.setPhonenumber(phonenumber);
            User user1 = this.userService.selectuserbyname(user);
            if (user1.getRole().equals("teacher")){
                // 这学期 一共要上几门实验课（老师 学生）
                count = this.teacherService.selectCourseCountC(user1.getId());
            }else {
                count = this.studentService.selectCourseCountC(user1.getId());
            }
            List<Teacher> teachers = this.teacherService.selecTeacherbyid(user1.getId());
            session.setAttribute("count",count);
            session.setAttribute("teachername",teachers);
            session.setAttribute("user",user1);
            return "main";
        }else{
            return "error/error";
        }
    }

    /*这个是验证你的用户名是否重复了*/
    @PostMapping("aoligei")
    public @ResponseBody User yanzheng(@RequestBody String username){
        User user = new User();
        user.setUsername(username);
        User user1 = this.userService.selectuserbyname(user);
        if (user1==null){
            return null;
        }else{
        return user1;
        }
    }

    /*发送短信验证码的*/
    @RequestMapping("phonelogins")
    public @ResponseBody Integer phonelogin(@RequestBody String phonenumber,HttpServletRequest request){
        if (phonenumber!=null){
        this.userService.sendphoneservice(phonenumber,request);
        return 1;
        }else {
            return 0;
        }
    }
}
