/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 8:21 下午
 * @Description:
 */
package com.zeal.tmall.service;


import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.pojo.PropertyValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertyValueService  {

    void update(PropertyValue bean);

    void delete(int id);

    void init(Product product);

    PropertyValue getByPropertyAndProduct(Product product, Property property);

    List<PropertyValue> list(Product product);

}
