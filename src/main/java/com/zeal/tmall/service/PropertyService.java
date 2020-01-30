/**
 * @Author: ZealYoung
 * @Time: 2020/1/21 2:24 下午
 * @Description:
 */
package com.zeal.tmall.service;

import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.util.Page4Navigator;

public interface PropertyService {

    Page4Navigator<Property> list(int cid, int start, int size,int navigatePages);

    void create(Property bean);

    void delete(int id);

    Property get(int id);

    void update(Property bean);

}
