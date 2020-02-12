/**
 * @Author: ZealYoung
 * @Time: 2020/1/30 11:29 上午
 * @Description:
 */
package com.zeal.tmall.service.imps;

import cn.hutool.crypto.SecureUtil;
import com.zeal.tmall.dao.UserDAO;
import com.zeal.tmall.pojo.User;
import com.zeal.tmall.service.UserService;
import com.zeal.tmall.util.Page4Navigator;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

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
    public boolean create(User user) {
        user.setRole("NORMAL");//防止跳过前端通过url构造管理员角色进行提交的情况，对于管理员权限，只能从数据库直接修改。
        String password = SecureUtil.md5(user.getPassword());
        user.setPassword(password);
        userDAO.save(user);
        return true;
    }

    @Override
    public User get(String name, String password) {
        password = SecureUtil.md5(password);
        return userDAO.getByNameAndPassword(name,password);
    }
}
