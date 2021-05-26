import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @Author shy Boy
 * @Date 2021/5/13 - 05 - 13 - 11:34
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
public class MyTest01 {
    //测试方法
    @Test
    public void test01() throws IOException {
        String urlStr = "https://www.baidu.com/";
        URL url = new URL(urlStr);
        URLConnection urlConnection = url.openConnection();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
        //获取httpsURLConnection的输入流
        try(
                InputStream is = httpsURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
           ){
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }

        }
    }
}
