/**
 * @Author: ZealYoung
 * @Time: 2020/2/11 5:59 下午
 * @Description:
 */
package com.zeal.tmall.interceptor;

import com.zeal.tmall.annotation.HasAuthority;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.pojo.enums.RoleAuthority;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AdminAuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截到了请求");
        if(!(handler instanceof HandlerMethod)){
            System.out.println(handler.getClass().getName());
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        HasAuthority methodAnnotation = method.getAnnotation(HasAuthority.class);

        System.out.println(method.getName());
        System.out.println(handlerMethod.getBean().getClass().getName());

        if(methodAnnotation != null){
            RoleAuthority roleAuthority = methodAnnotation.value();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null){
                return false;
            }
            if(roleAuthority.getCode() > RoleAuthority.getCode(user.getRole())){
                System.out.println("权限不足");
                response.sendRedirect("403");
                return false;
            }
        }
        return true;
    }
}
