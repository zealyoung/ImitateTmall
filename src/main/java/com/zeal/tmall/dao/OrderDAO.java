package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order,Integer> {

}
