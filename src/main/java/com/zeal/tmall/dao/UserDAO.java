package com.zeal.tmall.dao;

import com.zeal.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    User findByName(String name);

    User getByNameAndPassword(String name, String password);
}
