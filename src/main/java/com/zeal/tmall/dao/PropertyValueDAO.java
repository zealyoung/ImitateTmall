package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer> {

    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    List<PropertyValue> findByProperty(Property property);

    PropertyValue getByPropertyAndProduct(Property property, Product product);

    void deleteByProduct(Product product);

    void deleteByProperty(Property property);
}
