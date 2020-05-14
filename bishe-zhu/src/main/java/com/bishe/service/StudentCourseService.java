package com.bishe.service;


import com.bishe.entity.Course;
import com.bishe.entity.StudentCourse;
import com.bishe.entity.Teacher;
import com.bishe.mapper.CourseMapper;
import com.bishe.mapper.StudentCourseMapper;
import com.bishe.mapper.TeacherMapper;
import com.bishe.untils.DingTalkMessage;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentCourseService {

    @Value("${ding.curl}")
    private String curl;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private DingTalkMessage dingTalkMessage;


    public List<StudentCourse> selectCrouseNameLesson(String classes) {
        List<StudentCourse> courses = this.studentCourseMapper.selectCourseNameLesson(classes);
        return courses;
    }

    //  用来更新 这个班级的 这门课程的上课时间
    public void updatateacherclass(String banji, String str, String cname)  {
        if (StringUtils.isBlank(banji)&&StringUtils.isBlank(str)&&StringUtils.isBlank(cname)){
            return;
        }
        Integer integer = studentCourseMapper.updateTeacherclass(banji, str, cname);

        dingTalkMessage.sendDingMessage(banji+"的课程"+cname+":"+"修改时间到："+str,curl);
    }

    //  这个 服务 用来进行 查找这个班级对应的时间   有没有课
    public StudentCourse selectByBanJiAndTime(String banji, String time) {
        if (StringUtils.isBlank(banji)&StringUtils.isBlank(time)){
            return null;
        }
        StudentCourse studentCourse = this.studentCourseMapper.selectCourseByBanJiAndTime(banji, time);
        return  studentCourse;
    }


    @Transactional
    public void addcourse(Integer id, String c_name, String c_time, String s_classes, Integer num) {
        if (org.springframework.util.StringUtils.isEmpty(s_classes)&& org.springframework.util.StringUtils.isEmpty(c_name)&& org.springframework.util.StringUtils.isEmpty(c_time)&& org.springframework.util.StringUtils.isEmpty(num)){
            return;
        }
        //把课程先插入到课程表
        Course course = new Course();
        course.setName(c_name);
        course.setNum(num);
        int courid = this.courseMapper.insertSelective(course);
        if (courid==0){
            throw new RuntimeException("插入数据失败");
        }
        //插入老师的的表格
        List<Course> courses = this.courseMapper.select(course);
        Course course1 = courses.get(0);
        Teacher teacher = this.teacherMapper.selectByPrimaryKey(id);
        this.teacherMapper.insertcourse(course1.getId(),teacher.getId(),c_name,teacher.getName());

        //插入课程和学生的中间表
        // 昨天晚上发生了错误就是insert 后面不能跟 where语句， 那么怎么增加呢  就是把 学生号查找出来一一添加
        Course course2 = this.courseMapper.selectcoubyname(c_name);
        List<Integer> stuid = this.studentCourseMapper.selectstudentid(s_classes);
        for (Integer stid : stuid) {
            this.studentCourseMapper.insertcourse(new StudentCourse(stid,course2.getId(),s_classes,c_name,c_time));
        }
        String msg="老师增加了一门课程"+c_name+"上课时间是"+c_time;
        dingTalkMessage.sendDingMessage(msg,curl);

    }
}
