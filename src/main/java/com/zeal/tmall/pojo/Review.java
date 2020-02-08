/**
 * @Author: ZealYoung
 * @Time: 2020/2/2 5:51 下午
 * @Description:
 */
package com.zeal.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @ManyToOne
    @JoinColumn(name="pid")
    private Product product;

    private String content;

    private LocalDateTime createDate;

    @Tolerate
    public Review(){

    }
}
