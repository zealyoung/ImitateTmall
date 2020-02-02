/**
 * @Author: ZealYoung
 * @Time: 2020/2/2 8:54 下午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.ReviewDAO;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Review;
import com.zeal.tmall.service.ReviewService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImps implements ReviewService {
    @Autowired
    ReviewDAO reviewDAO;

    @Override
    public void add(Review review) {
        reviewDAO.save(review);
    }

    @Override
    public List<Review> list(Product product) {
        return reviewDAO.findByProductOrderByIdDesc(product);
    }

    @Override
    public int getCount(Product product) {
        return reviewDAO.countByProduct(product);
    }
}
