/**
 * @Author: ZealYoung
 * @Time: 2020/1/29 6:16 下午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.ProductDAO;
import com.zeal.tmall.dao.PropertyValueDAO;
import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.service.CategoryService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImps implements ProductService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    PropertyValueDAO propertyValueDAO;
    @Autowired
    CategoryService categoryService;

    @Override
    public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages) {
        Category category = categoryService.get(cid);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);

        Page<Product> pageFromJPA =productDAO.findByCategory(category,pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    @Override
    public List<Product> listByCategory(Category category) {
        return productDAO.findByCategoryOrderById(category);
    }

    @Override
    public void create(Product bean) {
        productDAO.save(bean);
    }

    @Override
    @Transactional
    public void delete(int id) {
        propertyValueDAO.deleteByProduct(productDAO.getOne(id));
        productDAO.deleteById(id);
    }

    @Override
    public Product get(int id) {
        return productDAO.findById(id).get();
    }

    @Override
    public void update(Product bean) {
        productDAO.save(bean);
    }
}
