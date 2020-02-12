/**
 * @Author: ZealYoung
 * @Time: 2020/1/21 2:39 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.annotation.HasAuthority;
import com.zeal.tmall.annotation.LoginRequired;
import com.zeal.tmall.pojo.Property;
import com.zeal.tmall.pojo.enums.RoleAuthority;
import com.zeal.tmall.service.PropertyService;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping(value = "/categories/{cid}/properties")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public Page4Navigator<Property> list(@PathVariable("cid") int cid,
                                         @RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5" ) int size) throws Exception{
        start = start < 0 ? 0 : start;
        Page4Navigator<Property> page = propertyService.list(cid, start, size,5);
        return page;
    }

    @PostMapping(value = "/properties")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public Object create(@RequestBody @Valid Property bean) {
        propertyService.create(bean);
        return bean;
    }

    @DeleteMapping("/properties/{id}")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public String delete(@PathVariable("id") int id)  throws Exception {
        propertyService.delete(id);
        return null;
    }

    @PutMapping("/properties")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public Object update(@RequestBody @Valid Property bean) throws Exception {
        propertyService.update(bean);
        return bean;
    }

    @GetMapping("/properties/{id}")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public Property get(@PathVariable("id") int id) throws Exception {
        Property bean = propertyService.get(id);
        return bean;
    }
}
