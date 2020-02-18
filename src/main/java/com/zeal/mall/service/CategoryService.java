/**
 * @Author: ZealYoung
 * @Time: 2020/1/20 11:35 上午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.util.Page4Navigator;

import java.util.List;

public interface CategoryService {

    /**
     * 分页查询
     * @param start
     * @param size
     * @param navigatePages 导航分页的个数
     */
    Page4Navigator<Category> list(int start, int size, int navigatePages);

    List<Category> list();

    void create(Category bean);

    void update(Category bean);

    void delete(int id);

    Category get(int id);

    void fillProduct(Category category);

    void fillProduct(List<Category> categories);

    //为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行
    void fillByRow(List<Category> categories);

    void removeCategoryFromProduct(List<Category> cs);

    void removeCategoryFromProduct(Category category);
}
