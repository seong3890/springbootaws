package com.springbootaws.domain.order;

import com.springbootaws.domain.product.Product;
import com.springbootaws.domain.time.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_list")
public class OrderList extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderPrice;

    private int count;


    // 생성 메서드

    public static OrderList createOrderList(Product product, int orderPrice, int count) {
        OrderList orderList = new OrderList(product, orderPrice, count);
        product.removeQuantity(count);
        return orderList;
    }
    // == 비즈니스 로직 ==

    public void cancel() {
        getProduct().addQuantity(count);
    }
    // 주문 상품 전체 가격 조회

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
    public void addOrder(Order order) {
        this.order = order;
    }

    public OrderList(Product product, int orderPrice, int count) {
        this.product = product;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}