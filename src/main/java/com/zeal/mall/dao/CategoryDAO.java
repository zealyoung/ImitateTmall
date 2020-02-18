/**
 * @Author: ZealYoung
 * @Time: 2020/1/20 11:32 上午
 * @Description:
 */
package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
