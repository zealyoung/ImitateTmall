/**
 * @Author: ZealYoung
 * @Time: 2020/1/16 10:31 上午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "传入名称为null")
    @NotEmpty(message = "传入名称为空")
    private String name;

    @Transient
    private List<Product> products;
    @Transient
    private List<List<Product>> productsByRow;
}
