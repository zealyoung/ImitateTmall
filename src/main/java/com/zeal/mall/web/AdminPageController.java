/**
 * @Author: ZealYoung
 * @Time: 2020/1/20 12:09 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.annotation.HasAuthority;
import com.zeal.tmall.annotation.LoginRequired;
import com.zeal.tmall.pojo.enums.RoleAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping(value="/admin")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public String admin(){
        return "redirect:admin_category_list";
    }

    @GetMapping(value="/admin_category_list")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public String listCategory(){
        return "admin/listCategory";
    }

    @GetMapping(value="/admin_category_edit")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public String editCategory() {
        return "admin/editCategory";
    }

    @GetMapping(value="/admin_order_list")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public String listOrder(){
        return "admin/listOrder";

    }

    @GetMapping(value="/admin_product_list")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public String listProduct(){
        return "admin/listProduct";

    }

    @GetMapping(value="/admin_product_edit")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public String editProduct(){
        return "admin/editProduct";

    }
    @GetMapping(value="/admin_productImage_list")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public String listProductImage(){
        return "admin/listProductImage";

    }

    @GetMapping(value="/admin_property_list")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public String listProperty(){
        return "admin/listProperty";

    }

    @GetMapping(value="/admin_property_edit")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public String editProperty(){
        return "admin/editProperty";

    }

    @GetMapping(value="/admin_propertyValue_edit")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public String editPropertyValue(){
        return "admin/editPropertyValue";

    }

    @GetMapping(value="/admin_user_list")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public String listUser(){
        return "admin/listUser";

    }

    @GetMapping(value = "403")
    public String errorFor403(){
        return "error/403";
    }
}
