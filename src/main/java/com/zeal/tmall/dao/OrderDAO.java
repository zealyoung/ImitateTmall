package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer> {

}
