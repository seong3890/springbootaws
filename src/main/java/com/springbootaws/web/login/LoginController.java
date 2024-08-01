package com.springbootaws.web.login;

import com.springbootaws.SessionConst;
import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberLoginService;
import com.springbootaws.domain.config.validator.LoginValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@Controller
public class LoginController {
    private final MemberLoginService memberLoginService;
    private final LoginValidator loginValidator;

    @InitBinder
    public void initValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(loginValidator);
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto") LoginDto loginDto) {
        return "account/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult,
                        HttpServletRequest request,
                        @RequestParam(defaultValue = "/",name = "requestURI") String requestURI) {
        if (bindingResult.hasErrors()) {
            return "account/login";
        }
        Member login = memberLoginService.login(loginDto.getUsername(), loginDto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, login);
        return "redirect:" + requestURI;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
