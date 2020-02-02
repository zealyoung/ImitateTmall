/**
 * @Author: ZealYoung
 * @Time: 2020/1/30 11:29 上午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import com.zeal.tmall.dao.UserDAO;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.service.UserService;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImps implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public Page4Navigator<User> list(int start, int size, int navigatePages) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size,sort);
        Page pageFromJPA =userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    @Override
    public boolean isExist(String name) {
        User user = userDAO.findByName(name);
        return user != null;
    }

    @Override
    public void create(User user) {
        userDAO.save(user);
    }

    @Override
    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name,password);
    }
}
