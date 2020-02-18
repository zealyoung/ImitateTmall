/**
 * @Author: ZealYoung
 * @Time: 2020/1/29 12:45 下午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.CategoryDAO;
import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.service.CategoryService;
import com.zeal.tmall.service.ProductImageService;
import com.zeal.tmall.service.ProductService;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImps implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;


    @Override
    public Page4Navigator<Category> list(int start, int size, int navigatePages) {

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page pageFromJPA =categoryDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    @Override
    public List<Category> list() {
        return categoryDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public void create(Category bean) {
        categoryDAO.save(bean);
    }

    @Override
    public void update(Category bean) {
        categoryDAO.save(bean);
    }

    @Override
    public void delete(int id) {
        categoryDAO.deleteById(id);
    }

    @Override
    public Category get(int id) {
        Category category= categoryDAO.findById(id).get();
        return category;
    }

    @Override
    public void fillProduct(Category category) {
        List<Product> products = productService.listByCategory(category);
        productImageService.setFirstProductImages(products);
        category.setProducts(products);
    }

    @Override
    public void fillProduct(List<Category> categories) {
        for(Category category : categories){
            fillProduct(category);
        }
    }

    @Override
    public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for (Category category : categories) {
            List<Product> products =  category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void removeCategoryFromProduct(List<Category> cs) {
        for (Category category : cs) {
            removeCategoryFromProduct(category);
        }
    }

    @Override
    public void removeCategoryFromProduct(Category category) {
        List<Product> products = category.getProducts();
        if (null != products) {
            for (Product product : products) {
                product.setCategory(null);
            }
        }
    }
}
