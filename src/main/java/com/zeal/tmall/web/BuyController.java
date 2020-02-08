/**
 * @Author: ZealYoung
 * @Time: 2020/2/5 7:40 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.OrderItem;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.pojo.enums.OrderStatus;
import com.zeal.tmall.service.OrderItemService;
import com.zeal.tmall.service.OrderService;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.util.Result;
import org.apache.commons.lang.math.RandomUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class BuyController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
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

    @GetMapping("/forechangeOrderItem")
    public Object changeOrderItem(int pid, int num, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Result.fail("未登录");
        }

        List<OrderItem> orderItems = orderItemService.listByUser(user);
        if(orderItems == null)
            return Result.fail("当前用户无订单");

        for(OrderItem orderItem : orderItems){
            if(orderItem.getProduct().getId() == pid && orderItem.getUser().getId() == user.getId() && orderItem.getOrder() != null){
                orderItem.setNumber(num);
                orderItemService.update(orderItem);
                return Result.success();
            }
        }

        return Result.fail("无法查询到对应订单或订单不属于当前用户");
    }

    @GetMapping("/foredeleteOrderItem")
    public Object deleteOrderItem(@RequestParam(value = "oiid") int orderItemId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return Result.fail("未登录");
        }

        Integer userId = Optional.ofNullable(orderItemService.get(orderItemId))
                                     .map(OrderItem::getUser)
                                     .map(User::getId)
                                     .orElse(-1);

        if(user.getId() != userId){
            return Result.fail("订单项不属于当前用户");
        }
        if(orderItemService.get(orderItemId).getOrder() != null){
            return Result.fail("当前订单项已经生成订单，无法删除");
        }

        orderItemService.delete(orderItemId);
        return Result.success();
    }

    @PostMapping("/forecreateOrder")
    public Object createOrder(Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null)
            return Result.fail("未登录");

        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setUser(user);
        order.setCreateDate(LocalDateTime.now());
        order.setStatus(OrderStatus.WAITPAY.getStatus());

        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("ois");
        float totalPrice = orderService.create(order, orderItems);

        Map<String,Object> map = new HashMap<>();
        map.put("oid", order.getId());
        map.put("total", totalPrice);

        return Result.success(map);
    }

    @GetMapping("/forepayed")
    public Object payed(@RequestParam(value = "oid") int orderId){
        Order order = orderService.get(orderId);
        order.setStatus(OrderStatus.WAITDELIVERY.getStatus());
        order.setPayDate(LocalDateTime.now());
        orderService.update(order);
        return order;
    }

    @GetMapping("forebought")
    public Object bought(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null)
            return Result.fail("未登录");
        List<Order> orders= orderService.listByUserWithoutDelete(user);
        return orders;
    }

    @GetMapping("/foreconfirmPay")
    public Object confirmPay(int oid) {
        Order order = orderService.get(oid);
        orderService.fill(order);
        orderService.cacl(order);
        return order;
    }

    @GetMapping("foreorderConfirmed")
    public Object orderConfirmed( int oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderStatus.WAITREVIEW.getStatus());
        order.setConfirmDate(LocalDateTime.now());
        orderService.update(order);
        return Result.success();
    }

    @PutMapping("foredeleteOrder")
    public Object deleteOrder(int oid){
        Order order= orderService.get(oid);
        order.setStatus(OrderStatus.DELETE.getStatus());
        orderService.update(order);
        return Result.success();
    }
}
