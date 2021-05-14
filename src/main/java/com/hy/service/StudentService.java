package com.hy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shy Boy
 * @Date 2021/4/29 - 04 - 29 - 11:40
 * @Description: com.hy.service
 * @version: 1.0
 */

public interface StudentService extends IService<Student> {
    List<Student> all();
}
