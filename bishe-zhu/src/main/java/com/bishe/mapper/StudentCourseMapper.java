package com.bishe.mapper;

import com.bishe.entity.Course;
import com.bishe.entity.StudentCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentCourseMapper extends Mapper<StudentCourse> {

    @Select("SELECT DISTINCT c_time,c_name FROM student_course WHERE c_time IS NOT NULL AND s_classes=#{classes}")
    List<StudentCourse> selectCourseNameLesson(String classes);


    @Update("UPDATE student_course SET c_time=#{str} WHERE s_classes=#{banji} AND c_name=#{cname};")
    void updateTeacherclass(String banji, String str, String cname);


    @Select("SELECT * FROM student_course WHERE s_classes=#{banji} AND c_time=#{time} GROUP BY(c_name)")
    StudentCourse selectCourseByBanJiAndTime(String banji, String time);

    @Insert("insert into student_course (s_id,c_id,c_name,c_time,s_classes) value (#{s_id},#{c_id},#{c_name},#{c_time},#{s_classes})")
    void insertcourse(StudentCourse studentCourse);

    // 查询班级为这个的课程
    @Select("SELECT DISTINCT s_id FROM student_course WHERE s_classes=#{s_classes} ")
    List<Integer> selectstudentid(String s_classes);

    //统计id为这个的学生的课程数量
    @Select("SELECT COUNT(DISTINCT(c_name)) FROM student_course  WHERE s_id=#{id} AND c_time IS NOT NULL;")
    Integer countcrou(Integer id);

    //通过学生id查询他的StudentCourse
    @Select("SELECT * FROM student_course sc  WHERE sc.`s_id`=#{id} AND sc.`c_time` IS NOT NULL")
    List<StudentCourse> selectSCbyid(Integer id);

    @Delete("DELETE FROM student_course WHERE c_name=#{value}")
    void deleteCourse(String value);
}
