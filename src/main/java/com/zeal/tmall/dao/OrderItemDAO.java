/**
 * @Author: ZealYoung
 * @Time: 2020/1/25 9:59 下午
 * @Description:
 */
package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
}

