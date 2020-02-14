/**
 * @Author: ZealYoung
 * @Time: 2020/2/14 4:25 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    @PostMapping(value = "foresearch")
    public Object search(String keyword){
        if(keyword == null || keyword == "")
            return new ArrayList<>();
        List<Product> products= productService.searchByName(keyword,0,20);
        productImageService.setFirstProductImages(products);
        productService.setReviewCount(products);
        return products;
    }
}
