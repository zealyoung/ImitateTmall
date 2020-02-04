/**
 * @Author: ZealYoung
 * @Time: 2020/2/4 11:28 上午
 * @Description:
 */
package com.zeal.tmall;

import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.service.CategoryService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.util.SortFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ForeCategoryTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    @Test
    public void test(){
        Category category = categoryService.get(83);
        categoryService.fillProduct(category);
        productService.setReviewCount(category.getProducts());
        categoryService.removeCategoryFromProduct(category);
        SortFactory.CategorySort(category.getProducts(), "price");
        for(Product product: category.getProducts()){
            System.out.println(product.getPromotePrice());
        }
    }
}
