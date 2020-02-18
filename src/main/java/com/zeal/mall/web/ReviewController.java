/**
 * @Author: ZealYoung
 * @Time: 2020/2/8 11:15 上午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.annotation.LoginRequired;
import com.zeal.tmall.pojo.Order;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Review;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.pojo.enums.OrderStatus;
import com.zeal.tmall.service.OrderService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.service.ReviewService;
import com.zeal.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @GetMapping("forereview")
    @LoginRequired
    public Object review(int oid) {
        Order order = orderService.get(oid);
        orderService.fill(order);
        //实际上应该对订单项的每个产品评价，但前端没有做订单下显示订单项的页面，所以偷懒只评价订单的第一个产品
        Product product = order.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.list(product);
        productService.setReviewCount(product);
        Map<String,Object> map = new HashMap<>();
        map.put("p", product);
        map.put("o", order);
        map.put("reviews", reviews);

        return Result.success(map);
    }

    @PostMapping("foredoreview")
    @LoginRequired
    @Transactional(rollbackFor = Exception.class)
    public Object doreview(HttpSession session, int oid, int pid, String content) {
        User user = (User) session.getAttribute("user");
        Order order = orderService.get(oid);
        if(order.getStatus() != OrderStatus.WAITREVIEW.getStatus()){
            return Result.fail("订单当前不是待评价状态");
        }
        if(order.getUser() == null || order.getUser().getId() != user.getId()){
            return Result.fail("订单不属于当前用户");
        }
        order.setStatus(OrderStatus.FINISH.getStatus());
        orderService.update(order);

        Product product = productService.get(pid);
        content = HtmlUtils.htmlEscape(content);

        Review review = Review.builder()
                              .user(user)
                              .content(content)
                              .product(product)
                              .createDate(LocalDateTime.now())
                              .build();
        reviewService.add(review);
        return Result.success();
    }
}
