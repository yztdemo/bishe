package com.bishe.mapper;

import com.bishe.entity.Student;
import tk.mybatis.mapper.common.Mapper;

public interface StudentMapper extends Mapper<Student> {
    void selectCouByID(Integer id);
}
