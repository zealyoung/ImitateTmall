/**
 * @Author: ZealYoung
 * @Time: 2020/1/30 11:21 上午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.OrderDAO;
import com.zeal.tmall.dao.OrderItemDAO;
import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.OrderItem;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.pojo.enums.OrderStatus;
import com.zeal.tmall.service.OrderItemService;
import com.zeal.tmall.service.OrderService;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.util.Page4Navigator;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImps implements OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderItemDAO orderItemDAO;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductImageService productImageService;

    @Override
    public Page4Navigator<Order> list(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page pageFromJPA =orderDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public float create(Order order, List<OrderItem> orderItems) {
        create(order);
        float totalPrice = 0;

        for(OrderItem orderItem : orderItems){
            orderItem.setOrder(order);
            orderItemService.update(orderItem);
            totalPrice += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }

        return totalPrice;
    }

    @Override
    public void create(Order order) {
        orderDAO.save(order);
    }

    @Override
    public Order get(int oid) {
        return orderDAO.findById(oid).get();
    }

    @Override
    public void update(Order bean) {
        orderDAO.save(bean);
    }

    @Override
    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    @Override
    public void fill(Order order) {
        List<OrderItem> orderItems = orderItemDAO.findByOrderOrderByIdDesc(order);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : orderItems) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
    }

    @Override
    public List<Order> listByUserWithoutDelete(User user) {
        List<Order> orders = orderDAO.findByUserAndStatusNotOrderByIdDesc(user, OrderStatus.DELETE.getStatus());
        fill(orders);
        return orders;
    }

    @Override
    public void cacl(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        float totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }
        order.setTotal(totalPrice);
    }
}
