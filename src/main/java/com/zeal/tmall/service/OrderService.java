/**
 * @Author: ZealYoung
 * @Time: 2020/1/27 12:30 上午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.util.Page4Navigator;

import java.util.List;

public interface OrderService {

    Page4Navigator<Order> list(int start, int size, int navigatePages);

    Order get(int oid);

    void update(Order bean);

    void fill(List<Order> orders);

    void fill(Order order);

}
