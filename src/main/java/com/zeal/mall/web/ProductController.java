/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 12:05 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.annotation.HasAuthority;
import com.zeal.tmall.annotation.LoginRequired;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.enums.RoleAuthority;
import com.zeal.tmall.service.CategoryService;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @GetMapping("/categories/{cid}/products")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public Page4Navigator<Product> list(@PathVariable("cid") int cid,
                                        @RequestParam(value = "start", defaultValue = "0") int start,
                                        @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start < 0 ? 0 : start;
        Page4Navigator<Product> page =productService.list(cid, start, size,5 );
        productImageService.setFirstProductImages(page.getContent());

        return page;
    }

    @PostMapping("/products")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public Object create(@RequestBody Product bean) throws Exception {
        bean.setCreateDate(LocalDateTime.now());
        productService.create(bean);
        return bean;
    }

    @DeleteMapping("/products/{id}")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public String delete(@PathVariable("id") int id)  throws Exception {
        productService.delete(id);
        return null;
    }

    @PutMapping("/products")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public Object update(@RequestBody Product bean) throws Exception {
        productService.update(bean);
        return bean;
    }

    @GetMapping("/products/{id}")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public Product get(@PathVariable("id") int id) throws Exception {
        Product bean=productService.get(id);
        return bean;
    }
}
