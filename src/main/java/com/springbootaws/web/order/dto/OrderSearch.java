package com.springbootaws.web.order.dto;

import com.springbootaws.domain.order.OrderList;
import com.springbootaws.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearch {
    private String nickname;
    private OrderStatus orderStatus;


}


