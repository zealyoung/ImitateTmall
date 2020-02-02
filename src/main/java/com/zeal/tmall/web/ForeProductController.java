/**
 * @Author: ZealYoung
 * @Time: 2020/2/2 9:03 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.ProductImage;
import com.zeal.tmall.pojo.PropertyValue;
import com.zeal.tmall.pojo.Review;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.service.PropertyValueService;
import com.zeal.tmall.service.ReviewService;
import com.zeal.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ForeProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;

    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) {
        Product product = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);

        List<PropertyValue> pvs = propertyValueService.list(product);
        List<Review> reviews = reviewService.list(product);
        product.setReviewCount(reviewService.getCount(product));
        productImageService.setFirstProductImage(product);

        Map<String,Object> map= new HashMap<>();
        map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);

        return Result.success(map);
    }
}
