package com.bishe.service;


import com.bishe.entity.Course;
import com.bishe.entity.Student;
import com.bishe.entity.Teacher;
import com.bishe.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    public List<Student> selecttclasses(String coursename) {
        List<Student> Students = this.teacherMapper.selectTClasses(coursename);
        return Students;
    }

    public List<Teacher> selecTeacherbyid(Integer id) {
       return this.teacherMapper.selecTeacherbyid(id);
    }

    public Integer selectCourseCountC(Integer id) {
        return this.teacherMapper.selectCourseCountC(id);
    }
}
