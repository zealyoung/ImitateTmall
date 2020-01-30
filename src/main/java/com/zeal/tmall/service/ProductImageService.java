/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 4:52 下午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String type_single = "single";
    String type_detail = "detail";

    void create(ProductImage bean);

    void delete(int id);

    ProductImage get(int id);

    List<ProductImage> listSingleProductImages(Product product);

    List<ProductImage> listDetailProductImages(Product product);

    void setFirstProductImage(Product product);

    void setFirstProductImages(List<Product> products);
}
