/**
 * @Author: ZealYoung
 * @Time: 2020/1/23 12:14 上午
 * @Description:
 */
package com.zeal.tmall;

import com.zeal.tmall.pojo.Product;
import com.zeal.tmall.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Test
    public void test() {
        User user = new User();
        user.setId(1);
        user.setName("zeal");
        user.setPassword("123");
        System.out.println(user.getAnonymousName());
    }

}
