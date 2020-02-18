/**
 * @Author: ZealYoung
 * @Time: 2020/1/27 11:03 上午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.annotation.HasAuthority;
import com.zeal.tmall.annotation.LoginRequired;
import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.enums.OrderStatus;
import com.zeal.tmall.pojo.enums.RoleAuthority;
import com.zeal.tmall.service.OrderService;
import com.zeal.tmall.util.Page4Navigator;
import com.zeal.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public Page4Navigator<Order> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                      @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start < 0 ? 0 : start;
        Page4Navigator<Order> page =orderService.list(start, size, 5);
        orderService.fill(page.getContent());
        return page;
    }

    @PutMapping("deliveryOrder/{oid}")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public Object deliveryOrder(@PathVariable int oid) throws IOException {
        Order order = orderService.get(oid);
        order.setDeliveryDate(LocalDateTime.now());
        order.setStatus(OrderStatus.WAITCONFIRM.getStatus());
        orderService.update(order);
        return Result.success();
    }
}
