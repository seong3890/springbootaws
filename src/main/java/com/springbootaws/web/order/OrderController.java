package com.springbootaws.web.order;

import com.springbootaws.domain.member.MemberService;
import com.springbootaws.domain.order.OrderService;

import com.springbootaws.web.member.dto.MemberNicknameDto;
import com.springbootaws.web.order.dto.OrderDto;
import com.springbootaws.web.order.dto.OrderSearch;
import com.springbootaws.web.product.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    @GetMapping("/orders")
    public String orderPageList(@PageableDefault(size = 5, page = 0) Pageable pageable,
                                @ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {

        Page<OrderDto> pageList = orderService.findPageList(orderSearch, pageable);
        model.addAttribute("order", pageList);

        return "order/orderList";
    }

    @GetMapping("/order")
    public String orderGet(Model model) {
        List<MemberNicknameDto> memberDto = memberService.findMemberDto();
        List<ProductDto> productDtos = orderService.findOrderDto();


        model.addAttribute("members", memberDto);
        model.addAttribute("products", productDtos);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String orderPost(@RequestParam("memberId") Long memberId,@RequestParam("productId") Long productId,@RequestParam("count") int count) {
        Long order = orderService.order(memberId, productId, count);
        return "redirect:/orders";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
