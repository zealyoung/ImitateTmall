/**
 * @Author: ZealYoung
 * @Time: 2020/2/5 7:40 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.OrderItem;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.service.OrderItemService;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BuyController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductImageService productImageService;

    @GetMapping(value = "/forebuyone")
    public Object buyOneOrAddCart(int pid, int num, HttpSession session) {
        Product product = productService.get(pid);
        User user = (User) session.getAttribute("user");

        //查询该用户是否有该产品未生成订单的订单项，如果有进行数量增加，没有则创建
        List<OrderItem> orderItems = orderItemService.listByUser(user);

        for(OrderItem orderItem : orderItems){
            if(orderItem.getProduct().getId() == product.getId()){
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.update(orderItem);
                return orderItem.getId();
            }
        }

        OrderItem orderItem = OrderItem.builder()
                                       .number(num)
                                       .product(product)
                                       .user(user).build();
        orderItemService.create(orderItem);
        return orderItem.getId();
    }

    @GetMapping("foreaddCart")
    public Object addCart(int pid, int num, HttpSession session) {
        buyOneOrAddCart(pid,num,session);
        return Result.success();
    }

    @GetMapping(value = "/forebuy")
    public Object buy(@RequestParam(value = "oiid") String[] orderItemsId, HttpSession session) {
        List<OrderItem> orderItems = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        float totalPrice = 0;

        for(String orderItemId : orderItemsId){
            try{
                Integer.parseInt(orderItemId);
            }catch (NumberFormatException e){
                //e.printStackTrace();
                return Result.fail("订单项id解析有误");
            }

            int id = Integer.parseInt(orderItemId);
            OrderItem orderItem = orderItemService.get(id);

            if(orderItem == null)
                return Result.fail("订单项不存在");
            else if(orderItem.getUser().getId() != user.getId())
                return Result.fail("此订单项不属于当前用户");

            totalPrice += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
            orderItems.add(orderItem);
        }

        productImageService.setFirstProductImagesOnOrderItems(orderItems);

        session.setAttribute("ois", orderItems);

        Map<String,Object> map = new HashMap<>();
        map.put("orderItems", orderItems);
        map.put("total", totalPrice);
        return Result.success(map);
    }

    @GetMapping("forecart")
    public Object cart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.listByUser(user);
        productImageService.setFirstProductImagesOnOrderItems(orderItems);
        return orderItems;
    }
}
