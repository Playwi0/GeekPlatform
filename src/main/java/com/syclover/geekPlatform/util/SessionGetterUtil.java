package com.syclover.geekPlatform.util;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * @Author cueyu
 * @Date 2020/9/14
 */

/*
* 方便直接在springsecurity的框架的session中
* 直接返回需要的username来认证用户*/

public class SessionGetterUtil {
    public static String getUsername(HttpSession session) throws Exception{
        try{
            Enumeration<String> attrs = session.getAttributeNames();
            String name = attrs.nextElement().toString();
            SecurityContextImpl value =(SecurityContextImpl) session.getAttribute(name);
            UserDetails principal = (UserDetails) value.getAuthentication().getPrincipal();
            String username = principal.getUsername();
            return username;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
