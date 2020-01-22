/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 8:32 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.PropertyValue;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception {
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product);
        return propertyValues;
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) throws Exception {
        propertyValueService.update(bean);
        return bean;
    }

    @DeleteMapping("propertyValues/{id}")
    public String delete(@PathVariable("id") int id)  throws Exception {
        propertyValueService.delete(id);
        return null;
    }
}
