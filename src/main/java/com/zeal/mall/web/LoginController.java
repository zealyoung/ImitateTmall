/**
 * @Author: ZealYoung
 * @Time: 2020/2/1 2:06 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.User;
import com.zeal.tmall.service.UserService;
import com.zeal.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/foreregister")
    public Object register(@RequestBody @Valid User user) {
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        if(userService.isExist(name)){
            String message = "用户名已被使用，请重新输入";
            return Result.fail(message);
        } else {
            boolean registerSuccess = userService.create(user);
            return registerSuccess ? Result.success() : Result.fail("注册信息有误");
        }
    }

    @PostMapping(value = "/forelogin")
    public Object login(@RequestBody User userGet, HttpSession session) {
        String name = userGet.getName();
        name = HtmlUtils.htmlEscape(name);

        User user = userService.get(name, userGet.getPassword());
        if(user == null){
            return Result.fail("账号密码有误");
        } else{
            session.setAttribute("user", user);
            return Result.success();
        }
    }

    @GetMapping("/forelogout")
    public void logout(HttpServletResponse response, HttpSession session) throws IOException {
        response.sendRedirect("home");
        session.removeAttribute("user");
    }

    @GetMapping("/forecheckLogin")
    public Object checkLogin( HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user != null)
            return Result.success();
        return Result.fail("未登录");
    }
}
