/**
 * @Author: ZealYoung
 * @Time: 2020/1/21 1:48 下午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "传入名称为null")
    @NotEmpty(message = "传入名称为空")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cid")
    @JsonBackReference
    private Category category;

}