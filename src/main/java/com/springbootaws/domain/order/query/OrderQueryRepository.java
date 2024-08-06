package com.springbootaws.domain.order.query;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootaws.domain.member.QMember;
import com.springbootaws.domain.order.OrderList;
import com.springbootaws.domain.order.OrderStatus;
import com.springbootaws.domain.order.QOrder;
import com.springbootaws.domain.order.QOrderList;
import com.springbootaws.domain.product.QProduct;
import com.springbootaws.web.order.dto.OrderDto;
import com.springbootaws.web.order.dto.OrderListDto;
import com.springbootaws.web.order.dto.OrderSearch;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.springbootaws.domain.member.QMember.member;
import static com.springbootaws.domain.order.QOrder.order;
import static com.springbootaws.domain.order.QOrderList.orderList;
import static com.springbootaws.domain.product.QProduct.product;
import static org.springframework.util.StringUtils.*;


@Repository
public class OrderQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory factory;

    public OrderQueryRepository(EntityManager em) {
        this.em = em;
        this.factory = new JPAQueryFactory(em);
    }

    /**
     * 인프런 JPA 쿼리 최적화 참고
     */
    public Page<OrderDto> findPageList(OrderSearch orderSearch, Pageable pageable) {
        // 다대일 관계 페이징 처리
        List<OrderDto> orderDtoList = factory.select(Projections.constructor(OrderDto.class,
                        order.id, member.nickname, order.orderStatus, order.CreateDateTime))
                .from(order)
                .join(order.member, member)
                .where(nicknameEq(orderSearch.getNickname()),
                        orderStatusEq(orderSearch.getOrderStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        /**
         * 일대다 OrderList를 Map으로 변환 (in 연산자로 최적화)
         * OrderIds(orderDtoList) <- 컬렉션 in으로 넣을 때 사용할 아이디 가져오기/
         * findOrderListMap()
         */

        Map<Long, List<OrderListDto>> longListMap = findOrderListMap(OrderIds(orderDtoList));
        // 다시 orderDtoList에 집어 넣음.
        orderDtoList.forEach(orderDto -> orderDto.setOrderLists(longListMap.get(orderDto.getId())));

        //count
        int size = factory.selectFrom(order)
                .join(order.member, member).fetchJoin()
                .where(nicknameEq(orderSearch.getNickname()),
                        orderStatusEq(orderSearch.getOrderStatus()))
                .fetch().size();


        return new PageImpl<>(orderDtoList, pageable, size);
    }

    private Map<Long, List<OrderListDto>> findOrderListMap(List<Long> orderId) {
        List<OrderListDto> orderListDtos = factory.select(Projections.constructor(OrderListDto.class,
                        orderList.id, product.name, orderList.orderPrice, orderList.count))
                .from(orderList)
                .join(orderList.product, product)
                .where(orderList.order.id.in(orderId))
                .fetch();
        // Map으로 만들어서 자바 메모리에 올리기
        Map<Long, List<OrderListDto>> collect = orderListDtos.stream().collect(Collectors.groupingBy(o -> o.getId()));
        return collect;
    }

    // 컬렉션 in으로 넣을 때 사용할 아이디 가져오기/
    private List<Long> OrderIds(List<OrderDto> orderDtoList) {
        return orderDtoList.stream().map(orderDto -> orderDto.getId()).toList();

    }

    private BooleanExpression orderStatusEq(OrderStatus orderStatus) {
        return orderStatus!=null ? order.orderStatus.eq(orderStatus) : null;
    }

    private BooleanExpression nicknameEq(String nickname) {
        return hasText(nickname) ? member.nickname.eq(nickname) : null;

    }
}
