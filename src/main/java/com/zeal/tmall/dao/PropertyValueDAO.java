package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer> {

    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    PropertyValue getByPropertyAndProduct(Property property, Product product);

    void deleteByProduct(Product product);

    void deleteByProperty(Property property);
}
