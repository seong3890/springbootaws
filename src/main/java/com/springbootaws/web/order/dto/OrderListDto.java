package com.springbootaws.web.order.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderListDto {
    private Long id; //주문 번호
    private String name; //상품 명
    private int orderPrice; //주문 가격
    private int count; //주문 수량

    public OrderListDto(Long id, String name, int orderPrice, int count) {
        this.id = id;
        this.name = name;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
