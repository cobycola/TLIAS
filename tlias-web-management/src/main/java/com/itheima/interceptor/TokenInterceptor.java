package com.itheima.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.itheima.utils.JwtUtils;
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取URL
        String requestURI = request.getRequestURI();
        //2.获取请求头中的token
        String token = request.getHeader("token");
        //3.判断token是否存在
        if(token==null||token.isEmpty()){
            log.info("token不存在");
            response.setStatus(401);
            return false;
        }
        //4.解析token
        try{
            JwtUtils.parseToken(token);
        }catch(Exception e){
            log.info("token不合法");
            response.setStatus(401);
            return false;
        }
        //5.放行
        log.info("token合法");
        return true;
    }
}
