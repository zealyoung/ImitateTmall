/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 11:33 下午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    private String name;

    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    private String password;


    private String role = "NORMAL";

    @Transient
    @Setter(AccessLevel.NONE)
    private String anonymousName;//匿名

    public String getAnonymousName() {
        if(anonymousName != null)
            return anonymousName;
        if(name == null)
            anonymousName = null;
        else if(name.length() <= 1)
            anonymousName = "*";
        else if(name.length() == 2)
            anonymousName = name.substring(0,1) + "*";
        else {
            StringBuffer sb = new StringBuffer();
            for(int i = 1; i < name.length() - 1; i++){
                sb.append("*");
            }
            anonymousName = name.substring(0,1) + sb.toString() + name.substring(name.length() - 1, name.length());
        }
        return anonymousName;
    }

}
