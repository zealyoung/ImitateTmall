/**
 * @Author: ZealYoung
 * @Time: 2020/1/21 1:48 下午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "property")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;

}