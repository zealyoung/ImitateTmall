/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 9:32 上午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    private String name;
    //产品标题
    private String subTitle;
    //原始价格
    private float originalPrice;
    //优惠价格
    private float promotePrice;

    private int stock;

    private LocalDateTime createDate;

    @Transient
    private ProductImage firstProductImage;
}
