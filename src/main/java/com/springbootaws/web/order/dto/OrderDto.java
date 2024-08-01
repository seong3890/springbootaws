package com.springbootaws.web.order.dto;

import com.springbootaws.domain.order.OrderList;
import com.springbootaws.domain.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String nickname;
    private List<OrderListDto> orderLists;
    private OrderStatus orderStatus;
    private String createTime;


    public OrderDto(Long id, String nickname,OrderStatus orderStatus, LocalDateTime createTime) {
        this.id = id;
        this.nickname = nickname;

        this.orderStatus = orderStatus;
        this.createTime = createTime.format((DateTimeFormatter.ofPattern("yy-mm-dd hh:ss")));
    }
}
