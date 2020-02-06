/**
 * @Author: ZealYoung
 * @Time: 2020/1/24 5:24 下午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="pid")
    private Product product;

    @ManyToOne
    @JoinColumn(name="oid")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name="uid")
    @JsonBackReference
    private User user;

    private int number;

    @Tolerate
    public OrderItem(){

    }
}
