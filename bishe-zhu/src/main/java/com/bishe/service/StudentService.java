package com.bishe.service;


import com.bishe.entity.StudentCourse;
import com.bishe.mapper.CourseMapper;
import com.bishe.mapper.StudentCourseMapper;
import com.bishe.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentCourseMapper scmapper;

    // 通过id 查询这学期有哪些课 还有 谁教  上几节课
    public List<StudentCourse> selectCourseById(Integer id) {
        return this.scmapper.selectSCbyid(id);
    }

    // 统计id为这个学生的课程数量
    public Integer selectCourseCountC(Integer id) {
        Integer countcrou = this.scmapper.countcrou(id);
        return countcrou;
    }
}
