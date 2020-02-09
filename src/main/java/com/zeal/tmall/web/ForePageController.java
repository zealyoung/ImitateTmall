/**
 * @Author: ZealYoung
 * @Time: 2020/1/31 4:36 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ForePageController {
    @GetMapping(value="/")
    public String index(){
        return "redirect:home";
    }
    @GetMapping(value="/home")
    public String home(){
        return "fore/home";
    }
    @GetMapping(value="/register")
    public String register(){
        return "fore/register";
    }
    @GetMapping(value="/alipay")
    @LoginRequired
    public String alipay(){
        return "fore/alipay";
    }
    @GetMapping(value="/bought")
    @LoginRequired
    public String bought(){
        return "fore/bought";
    }
    @GetMapping(value="/buy")
    @LoginRequired
    public String buy(){
        return "fore/buy";
    }
    @GetMapping(value="/cart")
    @LoginRequired
    public String cart(){
        return "fore/cart";
    }
    @GetMapping(value="/category")
    public String category(){
        return "fore/category";
    }
    @GetMapping(value="/confirmPay")
    @LoginRequired
    public String confirmPay(){
        return "fore/confirmPay";
    }
    @GetMapping(value="/login")
    public String login(){
        return "fore/login";
    }
    @GetMapping(value="/orderConfirmed")
    @LoginRequired
    public String orderConfirmed(){
        return "fore/orderConfirmed";
    }
    @GetMapping(value="/payed")
    @LoginRequired
    public String payed(){
        return "fore/payed";
    }
    @GetMapping(value="/product")
    public String product(){
        return "fore/product";
    }
    @GetMapping(value="/registerSuccess")
    public String registerSuccess(){
        return "fore/registerSuccess";
    }
    @GetMapping(value="/review")
    public String review(){
        return "fore/review";
    }
    @GetMapping(value="/search")
    public String searchResult(){
        return "fore/search";
    }
}
