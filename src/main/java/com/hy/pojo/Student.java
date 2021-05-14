package com.hy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shy Boy
 * @Date 2021/4/29 - 04 - 29 - 11:39
 * @Description: com.hy.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private String age;
}
