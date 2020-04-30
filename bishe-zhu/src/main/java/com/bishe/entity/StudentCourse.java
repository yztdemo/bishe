package com.bishe.entity;


import javax.persistence.Table;

@Table(name = "student_course")
public class StudentCourse {

    private Integer s_id;
    private Integer c_id;
    private String s_classes;
    private String c_name;
    private String c_time;

    public StudentCourse() {
    }

    public StudentCourse(Integer s_id, Integer c_id, String s_classses, String c_name, String c_time) {
        this.s_id = s_id;
        this.c_id = c_id;
        this.s_classes = s_classses;
        this.c_name = c_name;
        this.c_time = c_time;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getS_classses() {
        return s_classes;
    }

    public void setS_classses(String s_classses) {
        this.s_classes = s_classses;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "s_id=" + s_id +
                ", c_id=" + c_id +
                ", s_classses='" + s_classes + '\'' +
                ", c_name='" + c_name + '\'' +
                ", c_time='" + c_time + '\'' +
                '}';
    }
}
