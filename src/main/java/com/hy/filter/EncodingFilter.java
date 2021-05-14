package com.hy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author shy Boy
 * @Date 2021/4/29 - 04 - 29 - 17:18
 * @Description: com.hy.filter
 * @version: 1.0
 */
public class EncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //        设置请求编码格式
        req.setCharacterEncoding("utf-8");  //post 改变(请求实体)
        //        设置响应编码格式
        resp.setContentType("text/css;charset=utf-8");//修改响应编码
        chain.doFilter(req, resp);

    }

    public void destroy() {

    }
}
