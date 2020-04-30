package com.bishe.service;


import com.bishe.entity.Course;
import com.bishe.entity.CourseExt;
import com.bishe.entity.Student;
import com.bishe.entity.StudentCourse;
import com.bishe.mapper.CourseMapper;
import com.bishe.mapper.StudentCourseMapper;
import com.bishe.mapper.StudentMapper;
import com.bishe.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;


    public List<Course> selectteachertask(Integer id) {
        List<Course> courses = this.courseMapper.selectTeacherandCourse(id);
        if (CollectionUtils.isEmpty(courses)){
            return null;
        }else {
            return courses;
        }
    }
    //  通过 课程名 查询带 哪些班级 别且 什么时候上课
    public List<StudentCourse> selectTeacherCC(String c_name){
        if (StringUtils.isEmpty(c_name)){
            return null;
        }
        List<StudentCourse> coursescc = this.courseMapper.selectTeacherCC(c_name);
        return coursescc;
    }

    // 查找对应老师所教的课程 和课程的数量
    public List<CourseExt> selectCourseExt() {
        List<CourseExt> courseExts = this.courseMapper.selectCourseExt();
        return courseExts;
    }

    public Integer deleteCourse(String value) {
        if (value==null){
            return 0;
        }
        Course course = new Course();
        course.setName(value);
        this.teacherMapper.deleteCourse(value);
        this.studentCourseMapper.deleteCourse(value);
        int delete = this.courseMapper.delete(course);
        return delete;
    }
}
