/**
 * @Author: ZealYoung
 * @Time: 2020/1/27 12:30 上午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.dao.OrderDAO;
import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    public Page4Navigator<Order> list(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page pageFromJPA =orderDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public Order get(int oid) {
        return orderDAO.findById(oid).get();
    }

    public void update(Order bean) {
        orderDAO.save(bean);
    }
}
