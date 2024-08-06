package com.springbootaws.domain.order;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberJpaRepository;
import com.springbootaws.domain.order.query.OrderQueryRepository;
import com.springbootaws.domain.product.Product;
import com.springbootaws.domain.product.ProductJpaRepository;
import com.springbootaws.web.order.dto.OrderDto;
import com.springbootaws.web.order.dto.OrderSearch;
import com.springbootaws.web.product.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {
    private final OrderQueryRepository orderQueryRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public Page<OrderDto> findPageList(OrderSearch orderSearch, Pageable pageable) {
       return orderQueryRepository.findPageList(orderSearch, pageable);

    }

    public List<ProductDto> findOrderDto() {
        return productJpaRepository.findAll().stream().map(product -> new ProductDto(product.getId(),product.getName())).toList();

    }

    @Transactional
    public Long order(Long memberId, Long productId, int count) {
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("해당 회원이 존재하지 않습니다."));
        Product product = productJpaRepository.findById(productId).orElseThrow(() -> new IllegalStateException("해당 상품이 존재하지 않습니다."));
        Delivery delivery = new Delivery(member.getAddress(), DeliveryStatus.READY);
        OrderList orderList = OrderList.createOrderList(product, product.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderList);
        orderJpaRepository.save(order);
        return order.getId();

    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderJpaRepository.findById(orderId).orElseThrow();
        order.cancel();

    }
}
