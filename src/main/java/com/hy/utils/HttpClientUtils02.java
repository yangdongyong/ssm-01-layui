package com.hy.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author shy Boy
 * @Date 2021/5/25 - 05 - 25 - 15:57
 * @Description: com.hy.utils
 * @version: 1.0
 */
public class HttpClientUtils02 {


    /*
        请求JSON数据
     */
    @Test
    public void JSON(){
        //可关闭的httpclient客户端，相当于打开了杨过浏览器
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String urlStr = "http://localhost:8088/testJson";
        //构造httpGet请求对象(这些对象都是httpclient包下面的)
        HttpPost httpGet = new HttpPost(urlStr);

        //String是一个Json字符串
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","杨冬勇");
        jsonObject.put("age","20");
        jsonObject.put("age","男");
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), Consts.UTF_8);
        //也需要给Entity设置一下内容类型
        stringEntity.setContentType(new BasicHeader("Content-Type","application/json;charset=utf-8"));
        //设置Entity编码
        stringEntity.setContentEncoding(Consts.UTF_8.name());

        httpGet.setEntity(stringEntity);
        //可关闭的响应
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            //获取响应结果
            /*
                HttpEntity不仅可以作为请求的结果，也可以作为请求参数的实体，有很多的实现
             */
            HttpEntity entity = response.getEntity();
            //工具类，对HttpEntity操作的工具类
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            //确保流关闭
            EntityUtils.consume(entity);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (closeableHttpClient != null){
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }





    @Test
    public void timeOutTest(){
        //可关闭的httpclient客户端，相当于打开了杨过浏览器
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String urlStr = "https://www.google.cn/";
        //构造httpGet请求对象(这些对象都是httpclient包下面的)
        HttpGet httpGet = new HttpGet(urlStr);
        //对每一个请求进行配置，会覆盖全局的默认配置
        RequestConfig requestConfig = RequestConfig.custom()
                //设置连接超时，完成tcp三次握手时间上限
                .setConnectionRequestTimeout(500)
                //读取超时，ms表示从请求的网址处获得响应数据的时间间隔
                .setSocketTimeout(3000)
                //指的从链接池里面获得connection的超时时间
                .setConnectionRequestTimeout(5000)
                .build();
        httpGet.setConfig(requestConfig);
        //可关闭的响应
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            //获取响应结果
            /*
                HttpEntity不仅可以作为请求的结果，也可以作为请求参数的实体，有很多的实现
             */
            HttpEntity entity = response.getEntity();
            //工具类，对HttpEntity操作的工具类
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            //确保流关闭
            EntityUtils.consume(entity);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (closeableHttpClient != null){
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }






    @Test
    public void testProxy(){
        //可关闭的httpclient客户端，相当于打开了杨过浏览器
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String urlStr = "https://www.baidu.com/";
        //构造httpGet请求对象(这些对象都是httpclient包下面的)
        HttpGet httpGet = new HttpGet(urlStr);
         //创建一个代理
        String ip = "42.192.151.191";
        int port = 9999;
        HttpHost proxyHost = new HttpHost(ip, port);
        //对每一个请求进行配置，会覆盖全局的默认配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxyHost)
                //设置连接超时，完成tcp三次握手时间上限
                .setConnectionRequestTimeout(2000).build();
        httpGet.setConfig(requestConfig);
        //可关闭的响应
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            //获取响应结果
            /*
                HttpEntity不仅可以作为请求的结果，也可以作为请求参数的实体，有很多的实现
             */
            HttpEntity entity = response.getEntity();
            //工具类，对HttpEntity操作的工具类
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            //确保流关闭
            EntityUtils.consume(entity);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (closeableHttpClient != null){
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }






    /*
        获取网络上的图片并且保存到本地
     */
    @Test
    public void img(){

        //可关闭的httpclient客户端，相当于打开了杨过浏览器
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String urlStr = "http://i1.hdslb.com/bfs/archive/6a2455a00bfd319566103f53f20fd1cc6e2f942c.jpg@257w_145h_1c_100q.webp";
        //构造httpGet请求对象(这些对象都是httpclient包下面的)
        HttpGet httpGet = new HttpGet(urlStr);
        //可关闭的响应
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            //获取响应结果
            /*
                HttpEntity不仅可以作为请求的结果，也可以作为请求参数的实体，有很多的实现
             */
            HttpEntity entity = response.getEntity();
            //image/jpg image/jpgeg image/png image/图片的后缀
            String contentType = entity.getContentType().getValue();
            String sffix = ".jpg";
            if (contentType.contains("jpg") || contentType.contains("jpeg")){
                 sffix = ".jpg";
            }else if (contentType.contains("bmp") || contentType.contains("bitmap")){
                 sffix = ".bmp";
            }else if (contentType.contains("png")) {
                sffix = ".png";
            }else if (contentType.contains("gif")) {
                sffix = ".gif";
            }
            //获取文件的字节流
            byte[] bytes = EntityUtils.toByteArray(entity);
            String localAbsPath = "C:\\Users\\Lenovo\\Desktop\\s.bmp"+sffix;
            FileOutputStream fos = new FileOutputStream(localAbsPath);
            fos.write(bytes);
             fos.close();

            EntityUtils.consume(entity);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (closeableHttpClient != null){
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @Test
    public void testGet(){
        //可关闭的httpclient客户端，相当于打开了杨过浏览器
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String urlStr = "https://www.baidu.com/";
        //构造httpGet请求对象(这些对象都是httpclient包下面的)
        HttpGet httpGet = new HttpGet(urlStr);
        //解决httpclient被认为不是真人行为
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        //防盗链 value：发生防盗链的网站url
        httpGet.addHeader("Referer","https://www.baidu.com/");
        //可关闭的响应
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            //获取响应结果
            /*
                HttpEntity不仅可以作为请求的结果，也可以作为请求参数的实体，有很多的实现
             */
            HttpEntity entity = response.getEntity();
            //工具类，对HttpEntity操作的工具类
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            //确保流关闭
            EntityUtils.consume(entity);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (closeableHttpClient != null){
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
