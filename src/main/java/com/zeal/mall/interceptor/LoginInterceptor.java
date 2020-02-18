/**
 * @Author: ZealYoung
 * @Time: 2020/2/8 7:30 下午
 * @Description:
 */
package com.zeal.tmall.interceptor;

import com.zeal.tmall.annotation.LoginRequired;
import com.zeal.tmall.pojo.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截到了请求");
        if(!(handler instanceof HandlerMethod)){
            System.out.println(handler.getClass().getName());
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        System.out.println(method.getName());
        System.out.println(handlerMethod.getBean().getClass().getName());

        if(methodAnnotation != null){
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }
}
