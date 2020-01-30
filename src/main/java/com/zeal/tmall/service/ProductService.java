/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 11:53 上午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.util.Page4Navigator;

public interface ProductService  {

    Page4Navigator<Product> list(int cid, int start, int size, int navigatePages);

    void create(Product bean);

    void delete(int id);

    Product get(int id);

    void update(Product bean);

}
