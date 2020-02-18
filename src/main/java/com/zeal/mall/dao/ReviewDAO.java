package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDAO extends JpaRepository<Review,Integer> {

    List<Review> findByProductOrderByIdDesc(Product product);

    int countByProduct(Product product);

}

