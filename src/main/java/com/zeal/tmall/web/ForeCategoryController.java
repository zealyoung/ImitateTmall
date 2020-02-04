/**
 * @Author: ZealYoung
 * @Time: 2020/2/3 2:54 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.service.CategoryService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.util.SortFactory;
import org.aspectj.weaver.tools.cache.CacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class ForeCategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping(value = "/forecategory/{cid}")
    public Object categorySort(@PathVariable int cid, @RequestParam("sort") String sortMethod) {
        Category category = categoryService.get(cid);
        categoryService.fillProduct(category);
        productService.setReviewCount(category.getProducts());
        categoryService.removeCategoryFromProduct(category);
        SortFactory.CategorySort(category.getProducts(), sortMethod);
        return category;
    }

}
