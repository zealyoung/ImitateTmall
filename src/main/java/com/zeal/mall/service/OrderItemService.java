/**
 * @Author: ZealYoung
 * @Time: 2020/2/5 7:31 下午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.OrderItem;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.User;

import java.util.List;

public interface OrderItemService {

    void create(OrderItem orderItem);

    OrderItem get(int id);

    void update(OrderItem orderItem);

    void delete(int id);

    List<OrderItem> listByOrder(Order order);

    List<OrderItem> listByUser(User user);
}
