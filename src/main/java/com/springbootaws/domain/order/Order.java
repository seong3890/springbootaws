package com.springbootaws.domain.order;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.time.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Orders")
@Entity
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderList> orderLists = new ArrayList<>();

    public void addOrderList(OrderList orderList) {
        this.orderLists.add(orderList);
        orderList.addOrder(this);
    }

    public static Order createOrder(Member member, Delivery delivery, OrderList... orderList) {
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .orderStatus(OrderStatus.ORDER)
                .build();
        for (OrderList list : orderList) {
            order.addOrderList(list);
        }

        return order;
    }

    // 주문 취소

    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 주문 완료된 상품입니다.");
        }
        this.orderStatus = OrderStatus.CANCEL;
        for (OrderList orderList : orderLists) {
            orderList.cancel();
        }
    }


    @Builder
    public Order(Member member, Delivery delivery, OrderStatus orderStatus) {
        this.member = member;
        this.delivery = delivery;
        this.orderStatus = orderStatus;
    }

    @Builder
    public Order(Member member, Delivery delivery) {
        this.member = member;
        this.delivery = delivery;
    }
}

