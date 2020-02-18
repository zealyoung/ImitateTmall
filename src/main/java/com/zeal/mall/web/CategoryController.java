/**
 * @Author: ZealYoung
 * @Time: 2020/1/20 12:09 下午
 * @Description:
 */
package com.zeal.tmall.web;

import com.zeal.tmall.annotation.HasAuthority;
import com.zeal.tmall.annotation.LoginRequired;
import com.zeal.tmall.pojo.Category;
import com.zeal.tmall.pojo.enums.RoleAuthority;
import com.zeal.tmall.service.CategoryService;
import com.zeal.tmall.util.ImageUtil;
import com.zeal.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start < 0 ? 0 : start;
        Page4Navigator<Category> page =categoryService.list(start, size, 5);
        return page;
    }

    @PostMapping("/categories")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public Object create(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        categoryService.create(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

    @PutMapping("categories/{id}")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public Object update(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {

        categoryService.update(bean);

        if(image != null){
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }

    private void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {

        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,bean.getId()+".jpg");

        ImageUtil.writeImageJpg(image, file);
    }

    @DeleteMapping("/categories/{id}")
    @LoginRequired
    @HasAuthority(RoleAuthority.SUPER_ADMIN)
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    @GetMapping("/categories/{id}")
    @LoginRequired
    @HasAuthority(RoleAuthority.ADMIN)
    public Category get(@PathVariable("id") int id) throws Exception {
        Category bean=categoryService.get(id);
        return bean;
    }
}
