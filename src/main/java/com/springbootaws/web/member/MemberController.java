package com.springbootaws.web.member;

import com.springbootaws.domain.config.validator.MemberValidator;
import com.springbootaws.domain.member.MemberService;
import com.springbootaws.web.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @InitBinder
    public void initValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(memberValidator);
    }


    @GetMapping("/signup")
    public String memberSignForm(@ModelAttribute("memberDto") MemberDto memberDto, Model model) {
        return "member/account/signForm";
    }

    @PostMapping("/signup")
    public String MemberSignUp(@Validated MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/account/signForm";
        }
        memberService.signUp(memberDto);
        return "redirect:/";
    }
}
