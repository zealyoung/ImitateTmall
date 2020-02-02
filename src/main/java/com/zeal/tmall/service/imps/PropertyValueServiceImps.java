/**
 * @Author: ZealYoung
 * @Time: 2020/1/31 11:25 上午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.PropertyDAO;
import com.zeal.tmall.dao.PropertyValueDAO;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.pojo.PropertyValue;
import com.zeal.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImps implements PropertyValueService {
    @Autowired
    PropertyValueDAO propertyValueDAO;
    @Autowired
    PropertyDAO propertyDAO;

    public void update(PropertyValue bean) {
        propertyValueDAO.save(bean);
    }

    public void delete(int id) {
        propertyValueDAO.deleteById(id);
    }

    public void init(Product product) {
        List<Property> properties = propertyDAO.findByCategory(product.getCategory());
        for (Property property : properties) {
            PropertyValue propertyValue = getByPropertyAndProduct(product, property);
            if(propertyValue == null){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValue.setValue("");
                propertyValueDAO.save(propertyValue);
            }
        }
    }

    public PropertyValue getByPropertyAndProduct(Product product, Property property) {
        return propertyValueDAO.getByPropertyAndProduct(property,product);
    }

    public List<PropertyValue> list(Product product) {
        return propertyValueDAO.findByProductOrderByIdDesc(product);
    }
}
