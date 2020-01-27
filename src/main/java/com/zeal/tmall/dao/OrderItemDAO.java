/**
 * @Author: ZealYoung
 * @Time: 2020/1/25 9:59 下午
 * @Description:
 */
package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
}

