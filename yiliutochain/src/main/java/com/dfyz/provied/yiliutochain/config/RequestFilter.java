package com.dfyz.provied.yiliutochain.config;

import com.alibaba.fastjson.JSONObject;
import com.dfyz.provied.yiliutochain.common.Common;
import com.dfyz.provied.yiliutochain.common.CommonResponse;
import com.dfyz.provied.yiliutochain.util.RSAUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

//@Configuration
@Order(1)
@WebFilter(filterName = "piceaFilter", urlPatterns = "/*")
public class RequestFilter implements Filter {
    private String sign;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String publicKey = httpServletRequest.getHeader("Authorization");
            if (!StringUtils.isNotBlank(publicKey)) {
                getWriter(servletResponse).close();
                return;
            } else {
                try {
                    if (!StringUtils.isNotBlank(this.sign)) {
                        this.sign = RSAUtil.sign(Common.PROJECT_ID, RSAUtil.getPrivateKey(Common.PRIVATE_KEY));
                    }
                    if (RSAUtil.verify(Common.PROJECT_ID, RSAUtil.getPublicKey(publicKey), this.sign))
                        filterChain.doFilter(servletRequest, servletResponse);
                } catch (Exception e) {
                    System.err.println("公钥不正确！");
                    getWriter(servletResponse).close();
                }
            }

        }
    }

    @Override
    public void destroy() {
    }

    private PrintWriter getWriter(ServletResponse response) throws IOException {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(new CommonResponse(403, false, "公钥缺失或不正确！")));
        writer.flush();
        return writer;

    }
}
