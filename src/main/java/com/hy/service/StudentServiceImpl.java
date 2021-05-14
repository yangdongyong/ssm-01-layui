package com.hy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.mapper.StudentMapper;
import com.hy.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shy Boy
 * @Date 2021/4/29 - 04 - 29 - 11:41
 * @Description: com.hy.service
 * @version: 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    public List<Student> all() {
        List<Student> students = baseMapper.selectList(null);
        return students;
    }
}
