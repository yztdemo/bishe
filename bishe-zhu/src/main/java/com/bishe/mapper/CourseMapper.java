package com.bishe.mapper;

import com.bishe.entity.Course;
import com.bishe.entity.CourseExt;
import com.bishe.entity.StudentCourse;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CourseMapper extends Mapper<Course> {


    @Select("SELECT c.* FROM teach_course tc ,course c WHERE tc.`t_id`=#{id} AND tc.`c_id`=c.`id`;")
    List<Course> selectTeacherandCourse(Integer id);


    @Select("SELECT DISTINCT c_time,s_classes,c_name FROM student_course WHERE c_name=#{c_name}")
    List<StudentCourse> selectTeacherCC(String c_name);

    @Select("SELECT * FROM course WHERE NAME=#{c_name}")
    Course selectcoubyname(String c_name);

    @Select("SELECT tc.`t_name`,c.* FROM teach_course tc , course c WHERE tc.`c_id`= c.`id`;")
    List<CourseExt> selectCourseExt();
}
