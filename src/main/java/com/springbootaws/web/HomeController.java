package com.springbootaws.web;

import com.springbootaws.SessionConst;
import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.product.BookService;
import com.springbootaws.web.product.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class HomeController {
    private final BookService bookService;
    @GetMapping
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        List<ProductDto> productDtos = bookService.BookTop10();
        log.info("product.memberName={}",productDtos.get(0).getNickname());
        model.addAttribute("product", productDtos);
        if (member == null) {
            return "/home/home";
        }
        Long memberId = member.getId();
        String nickname = member.getNickname();
        model.addAttribute("memberId", memberId);
        model.addAttribute("nickName", nickname);
        return "home/loginHome";
    }


}
