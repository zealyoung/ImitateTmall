/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 9:32 上午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "传入名称为null")
    @NotEmpty(message = "传入名称为空")
    private String name;

    private String subTitle;

    @NotNull(message = "传入名称为null")
    private float originalPrice;

    private float promotePrice;

    @NotNull(message = "传入名称为null")
    @Min(value = 0, message = "库存余量至少为0")
    private int stock;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    @Transient
    private ProductImage firstProductImage;
}
