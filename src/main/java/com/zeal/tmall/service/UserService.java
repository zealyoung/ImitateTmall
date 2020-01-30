/**
 * @Author: ZealYoung
 * @Time: 2020/1/23 12:31 上午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.User;
import com.zeal.tmall.util.Page4Navigator;

public interface UserService {

    Page4Navigator<User> list(int start, int size, int navigatePages);

}
