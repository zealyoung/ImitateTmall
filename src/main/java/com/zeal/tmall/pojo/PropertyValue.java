/**
 * @Author: ZealYoung
 * @Time: 2020/1/22 7:58 下午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "propertyvalue")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class PropertyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="pid")
    private Product product;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="ptid")
    private Property property;

    private String value;
}
