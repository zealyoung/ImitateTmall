/**
 * @Author: ZealYoung
 * @Time: 2020/2/3 3:06 下午
 * @Description:
 */
package com.zeal.tmall.util;

import com.zeal.tmall.pojo.Product;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortFactory {
    public static void CategorySort(List<Product> products, String sortMethod) {

        if(sortMethod != null){
            switch (sortMethod){
                case "review":
                    products.sort((Product p1, Product p2) -> p2.getReviewCount() - p1.getReviewCount());
                    break;
                case "date":
                    products.sort(Comparator.comparing(Product::getCreateDate).reversed());
                    break;
                case "saleCount":
                    products.sort((Product p1, Product p2) -> p2.getSaleCount() - p1.getSaleCount());
                    break;
                case "price":
                    products.sort((Product p1, Product p2) -> (int) (p1.getPromotePrice() - p2.getPromotePrice()));
                    break;
                case "all":
                    products.sort((p1, p2) -> p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount());
                    break;
                default:
                    System.out.println("sortMethod有误");
                    break;
            }
        }

    }
}
