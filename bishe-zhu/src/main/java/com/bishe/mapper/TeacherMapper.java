package com.bishe.mapper;

import com.bishe.entity.Course;
import com.bishe.entity.Student;
import com.bishe.entity.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherMapper extends Mapper<Teacher> {


    @Select(" SELECT DISTINCT(classes) FROM student s INNER JOIN student_course sc  ON sc.`c_name`=#{c_name} AND s.`id`=sc.`s_id` ")
    List<Student> selectTClasses(String c_name);

    @Insert("INSERT INTO teach_course (c_id,t_id,c_name,t_name) VALUES (#{id},#{id1},#{c_name},#{name})")
    void insertcourse(Integer id, Integer id1, String c_name, String name);

    @Select("SELECT t.`name` FROM USER u LEFT JOIN teach_student ts ON ts.`s_id`=u.`id` LEFT JOIN teacher t ON ts.`t_id`= t.`id` WHERE u.`id`=#{id}")
    List<Teacher> selecTeacherbyid(Integer id);

    @Select("SELECT COUNT(*) FROM teach_course tc ,course c WHERE tc.`t_id`=#{id} AND tc.`c_id`=c.`id`;")
    Integer selectCourseCountC(Integer id);

    @Delete("DELETE FROM teach_course WHERE c_name=#{value}")
    void deleteCourse(String value);
}
