/**
 * @Author: ZealYoung
 * @Time: 2020/1/29 1:48 下午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.ProductDAO;
import com.zeal.tmall.dao.PropertyDAO;
import com.zeal.tmall.dao.PropertyValueDAO;
import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.service.CategoryService;
import com.zeal.tmall.service.PropertyService;
import com.zeal.tmall.service.PropertyValueService;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropertyServiceImps implements PropertyService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    PropertyDAO propertyDAO;
    @Autowired
    PropertyValueDAO propertyValueDAO;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    CategoryService categoryService;

    @Override
    public Page4Navigator<Property> list(int cid, int start, int size, int navigatePages) {
        Category category = categoryService.get(cid);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Property> pageFromJPA =propertyDAO.findByCategory(category,pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }

    @Override
    @Transactional
    public void create(Property bean) {
        propertyDAO.save(bean);
        //进行产品属性初始化
        List<Product> products = productDAO.findByCategory(bean.getCategory());
        for(Product product : products){
            propertyValueService.init(product);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        propertyValueDAO.deleteByProperty(propertyDAO.findById(id).get());
        propertyDAO.deleteById(id);
    }

    @Override
    public Property get(int id) {
        return propertyDAO.findById(id).get();
    }

    @Override
    public void update(Property bean) {
        propertyDAO.save(bean);
    }
}
