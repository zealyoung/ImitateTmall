/**
 * @Author: ZealYoung
 * @Time: 2020/1/27 12:30 上午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.OrderItem;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.util.Page4Navigator;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    Page4Navigator<Order> list(int start, int size, int navigatePages);

    void create(Order order);

    float create(Order order, List<OrderItem> orderItems);

    Order get(int oid);

    void update(Order bean);

    void fill(List<Order> orders);

    void fill(Order order);

    void cacl(Order o);

    List<Order> listByUserWithoutDelete(User user);
}
