/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 4:51 下午
 * @Description:
 */
package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDAO extends JpaRepository<ProductImage,Integer> {
    public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);

}
