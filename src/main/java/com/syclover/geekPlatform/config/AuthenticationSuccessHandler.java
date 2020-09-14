package com.syclover.geekPlatform.config;

import com.alibaba.fastjson.JSON;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @Author cueyu
 * @Date 2020/9/14
 */

@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException, NoSuchElementException {
        try{
            HttpSession session = request.getSession();
            String username = SessionGetterUtil.getUsername(session);
            User data = userService.getLoginUser(username).getData();
            data.setPassword(null);
            ResultT result = new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),data);
            httpServletResponse.getWriter().write(JSON.toJSONString(result.toString()));
        }catch (Exception e){
            httpServletResponse.getWriter().write("something wrong!");
        }
    }
}
