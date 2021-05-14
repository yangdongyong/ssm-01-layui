package com.hy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shy Boy
 * @Date 2021/5/10 - 05 - 10 - 15:38
 * @Description: com.hy.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Integer id;
    @TableField(value = "userName")
    private String userName;
    @TableField(value = "passWord")
    private String passWord;
    private String salt;
}
