package com.tensquare.qa.intercept;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/12 13:29
 * @version: v1.0.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //鉴权
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            try {
                String roles = (String) claims.get("roles");
                if (!StringUtils.isEmpty(roles) && roles.equals("admin"))
                    request.setAttribute("admin_claims", claims);
                if (!StringUtils.isEmpty(roles) && roles.equals("user"))
                    request.setAttribute("user_claims", claims);
            } catch (Exception e) {
                throw new RuntimeException("令牌不正确或已经失效");
            }
        }
        return true;
    }
}
