/**
 * @Author: ZealYoung
 * @Time: 2020/1/31 4:36 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForeHomeController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/forehome")
    public Object home() {
        List<Category> cs = categoryService.list();
        categoryService.fillProduct(cs);
        categoryService.fillByRow(cs);
        categoryService.removeCategoryFromProduct(cs);
        return cs;
    }
}
