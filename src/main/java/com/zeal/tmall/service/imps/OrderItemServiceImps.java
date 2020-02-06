/**
 * @Author: ZealYoung
 * @Time: 2020/2/5 7:33 下午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.OrderItemDAO;
import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.OrderItem;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImps implements OrderItemService {
    @Autowired
    OrderItemDAO orderItemDAO;

    @Override
    public void create(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    @Override
    public OrderItem get(int id) {
        return orderItemDAO.findById(id).get();
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemDAO.deleteById(id);
    }

    @Override
    public List<OrderItem> listByOrder(Order order) {
        return orderItemDAO.findByOrderOrderByIdDesc(order);
    }

    @Override
    public List<OrderItem> listByUser(User user) {
        return orderItemDAO.findByUserAndOrderIsNull(user);
    }
}
