/**
 * @Author: ZealYoung
 * @Time: 2020/1/24 5:27 下午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zeal.tmall.pojo.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    private String orderCode;

    private String address;
    @Column(name = "post")
    private String postCode;

    private String receiver;

    private String mobile;

    private String userMessage;

    private LocalDateTime createDate;

    private LocalDateTime payDate;

    private LocalDateTime deliveryDate;

    private LocalDateTime confirmDate;

    private String status;

    @Transient
    private List<OrderItem> orderItems;
    @Transient
    private float total;
    @Transient
    private int totalNumber;
    @Transient
    @Setter(AccessLevel.NONE)
    private String statusDesc;

    public String getStatusDesc() {
        if(statusDesc != null)
            return statusDesc;
        statusDesc = OrderStatus.getInfo(status);
        return statusDesc;
    }

}
