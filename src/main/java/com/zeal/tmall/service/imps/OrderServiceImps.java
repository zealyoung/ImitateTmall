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
import com.zeal.tmall.service.OrderService;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImps implements OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderItemDAO orderItemDAO;
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
}
