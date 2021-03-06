/**
 * @Author: ZealYoung
 * @Time: 2020/1/29 6:29 下午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.ProductImageDAO;
import com.zeal.tmall.pojo.OrderItem;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.ProductImage;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImps implements ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Autowired
    ProductImageDAO productImageDAO;
    @Autowired
    ProductService productService;

    @Override
    public void create(ProductImage bean) {
        productImageDAO.save(bean);

    }
    @Override
    public void delete(int id) {
        productImageDAO.deleteById(id);
    }

    @Override
    public ProductImage get(int id) {
        return productImageDAO.findById(id).get();
    }

    @Override
    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_single);
    }

    @Override
    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage()); //产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }

    @Override
    public void setFirstProductImages(List<Product> products) {
        for (Product product : products)
            setFirstProductImage(product);
    }

    @Override
    public void setFirstProductImagesOnOrderItems(List<OrderItem> ois) {
        for (OrderItem orderItem : ois) {
            setFirstProductImage(orderItem.getProduct());
        }
    }
}
