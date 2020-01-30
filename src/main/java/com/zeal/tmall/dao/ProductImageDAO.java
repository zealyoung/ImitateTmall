/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 4:51 下午
 * @Description:
 */
package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageDAO extends JpaRepository<ProductImage,Integer> {
    List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);

}
