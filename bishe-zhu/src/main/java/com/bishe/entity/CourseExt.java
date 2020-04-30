package com.bishe.entity;


public class CourseExt extends Course{
    private String t_name;

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    @Override
    public String toString() {
        return "CourseExt{" +
                "course=" +t_name+super.toString()+
                '}';
    }
}
