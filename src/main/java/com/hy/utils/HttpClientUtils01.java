package com.hy.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.xpath.operations.Mult;
import sun.net.www.http.HttpClient;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;

/**
 * @Author shy Boy
 * @Date 2021/5/24 - 05 - 24 - 14:47
 * @Description: com.hy.utils
 * @version: 1.0
 */
public class HttpClientUtils01 {
   private static  CloseableHttpResponse execute = null;
   private static  CloseableHttpClient aDefault = null;
   private static   HttpEntity entity  = null;
   private static String s = null;
    //发送get请求
    public static String requestGet() throws IOException {

        try {
            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
             aDefault = HttpClients.createDefault();
            //组装请求对象
            HttpGet httpGet = new HttpGet("http://localhost:8088/md/85023/20");
            //发送请求
             execute = aDefault.execute(httpGet);
            //请求状态
            System.out.println(execute.getStatusLine());
            //得到响应内容
             entity = execute.getEntity();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            execute.close();
            aDefault.close();
        }
        //对象转换成string类型
        return s;

    }



    //发送post请求
    public static String requestPost() throws IOException {
        try {
            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
            aDefault = HttpClients.createDefault();
            //组装请求对象
            HttpPost httpPost = new HttpPost("http://localhost:8088/md/85023/20");
            //发送请求
            execute = aDefault.execute(httpPost);
            //请求状态
            System.out.println(execute.getStatusLine());
            //得到响应内容
            entity = execute.getEntity();
             s = EntityUtils.toString(entity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            execute.close();
            aDefault.close();
        }
        //对象转换成string类型
        return s;

    }


    //json请求
    public static String  senPostJson() throws IOException {
        try {

            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
            aDefault = HttpClients.createDefault();
            //组装请求对象
            HttpPost httpPost = new HttpPost("http://localhost:8088/sc");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data","1");
            jsonObject.put("file","10");
            //设置实体内容
            httpPost.setEntity(new StringEntity(jsonObject.toJSONString()));
            httpPost.setHeader("Content-Type","application/json;charset=utf-8");
            //发送请求
            CloseableHttpResponse execute = aDefault.execute(httpPost);
            //请求状态
            System.out.println(execute.getStatusLine());
            //得到响应内容
            HttpEntity entity = execute.getEntity();

            s = EntityUtils.toString(entity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            execute.close();
            aDefault.close();
        }
        return s;
    }

    //上传文件
    public static String  senPostJsonUploaFiel() throws IOException {
        try {

            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
            aDefault = HttpClients.createDefault();
            //组装请求对象
            HttpPost httpPost = new HttpPost("http://localhost:8088/sc");
            //设置编码方式防止中文乱码
            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
            //上传文件组装对象
            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    //设置文本内容
                    .addTextBody("name","杨过",contentType)
                    .addTextBody("age","30")
                    //设置文件内容
                    .addBinaryBody("file",new File("C:\\Users\\Lenovo\\Desktop\\s.bmp.jpg"));
            //设置实体内容
            httpPost.setEntity(builder.build());
//            //设置请求数据类型 允许上传二进制文件
//            httpPost.setHeader("Content-type","multipart/form-data");
            //发送请求
             execute = aDefault.execute(httpPost);
            //请求状态
            System.out.println(execute.getStatusLine());
            //得到响应内容
            HttpEntity entity = execute.getEntity();
            //对象转换为String类型
            s = EntityUtils.toString(entity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            execute.close();
            aDefault.close();
        }
        return s;
    }






    public static void main(String[] args) {
        try {
//              requestGet();
//            System.out.println(s);
//            requestPost();
//            String s = senPostJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            System.out.println(jsonObject.getString("code"));
//            senPostJsonUploaFiel();
            senPostJsonUploaFiel();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
