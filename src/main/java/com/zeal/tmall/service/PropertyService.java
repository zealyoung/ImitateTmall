/**
 * @Author: ZealYoung
 * @Time: 2020/1/21 2:24 下午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.dao.PropertyDAO;
import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    PropertyDAO propertyDAO;

    @Autowired
    CategoryService categoryService;

    public Page4Navigator<Property> list(int cid, int start, int size,int navigatePages) {
        Category category = categoryService.get(cid);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);

        Page<Property> pageFromJPA =propertyDAO.findByCategory(category,pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }

    public void add(Property bean) {
        propertyDAO.save(bean);
    }

    public void delete(int id) {
        propertyDAO.deleteById(id);
    }

    public Property get(int id) {
        return propertyDAO.findById(id).get();
    }

    public void update(Property bean) {
        propertyDAO.save(bean);
    }

}
