package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    Page<Product> findByCategory(Category category, Pageable pageable);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryOrderById(Category category);

    List<Product> findByNameLike(String keyword, Pageable pageable);
}
