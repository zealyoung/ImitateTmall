/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 11:53 上午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.util.Page4Navigator;

import java.util.List;

public interface ProductService  {

    Page4Navigator<Product> list(int cid, int start, int size, int navigatePages);

    List<Product> listByCategory(Category category);

    void create(Product bean);

    void delete(int id);

    Product get(int id);

    void update(Product bean);

    void setReviewCount(List<Product> products);

    void setReviewCount(Product product);

    List<Product> searchByName(String name, int start, int size);
}
