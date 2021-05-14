package com.hy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.pojo.Student;
import com.hy.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shy Boy
 * @Date 2021/4/29 - 04 - 29 - 11:40
 * @Description: com.hy.controller
 * @version: 1.0
 */
@Controller
public class StudentController {
    @Autowired
    private  StudentService studentService ;

    @RequiresPermissions( value = {"login:user"})
        @RequestMapping("/stu/all")
        @ResponseBody
        public Map select(int page,int limit,String name){
            QueryWrapper<Student> wrapper = new QueryWrapper();
            wrapper.like(name != null && !"".equals(name),"name",name);
            Map map = new HashMap();
            Page<Student> studentPage = studentService.page(new Page(page, limit),wrapper);
            map.put("code",0);
            map.put("msg","成功");
            map.put("count",studentPage.getTotal());//总数量
            map.put("data",studentPage.getRecords());//记录

            return map;
        }

            @RequestMapping("/test/jump")
            public String jump(){
            return "StudentTable01";
            }

            @RequestMapping("/test/temp")
            public String temp(){
                return "test01";
            }

            @ResponseBody
            @RequestMapping("/test/insert")
            public boolean insert(Student student){
                System.out.println(student);
                return studentService.save(student);
            }

            @ResponseBody
            @RequestMapping("/test/delete")
            public void delete(Integer id){
                studentService.removeById(id);
            }



            @RequestMapping("/test/ss")
            public String skip(){
                return "InsertStudent";
            }

           // 跳转登录页面
            @RequestMapping("/test/login")
            public String login(){
                return "logins";
            }

            //接收用户登录账号密码
            //@RequiresPermission：权限关系 AND OR

            @RequestMapping("/login/user")
            @ResponseBody
            public Map loginUser(String username,String password){
            Map map=new HashMap();

            try {
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(username,password));
                map.put("code","200");
            }catch ( AuthenticationException e){
                e.printStackTrace();

                map.put("code","error");

                map.put("msg","404");
            }

             return map;


            }

            // 跳转测试
            @RequestMapping("/test/dome")
            public String dome(){
                return "StudentDemo01";
            }




}
