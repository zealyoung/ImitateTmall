package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {

    void add(Review review);

    List<Review> list(Product product);

    int getCount(Product product);
}
