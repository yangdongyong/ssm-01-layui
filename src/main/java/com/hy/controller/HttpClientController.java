package com.hy.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author shy Boy
 * @Date 2021/5/24 - 05 - 24 - 14:58
 * @Description: com.hy.controller
 * @version: 1.0
 */
@Controller
public class HttpClientController {


    @ResponseBody
    @RequestMapping("/md/{name}/{age}")
    public String test01(@PathVariable String name, @PathVariable String age){
       return name+""+age;
    }

    @ResponseBody
    @RequestMapping("/sc")
    public JSONObject test02(   MultipartFile file, String name) throws IOException {
        String originalFilename = file.getOriginalFilename();
        file.transferTo(new File("C:\\Users\\Lenovo\\Desktop\\zzz.jpg"));
        //解析json字符串
//        JSONObject jsonObject = JSONObject.parseObject(name);
//        System.out.println(jsonObject.getString("name"));
//        System.out.println(jsonObject.getString("age"));

        JSONObject object = new JSONObject();
        object.put("code","success");
        return object;
    }


    @RequestMapping("/testJson")
    public String testJson(@RequestBody String userRequet){

        System.out.println("业务入参"+userRequet);
        //解析JSON字符串
        JSONObject jsonObject = JSONObject.parseObject(userRequet);
        System.out.println(jsonObject.getString("name"));
        System.out.println(jsonObject.getString("age"));
        return "json";
    }
}
