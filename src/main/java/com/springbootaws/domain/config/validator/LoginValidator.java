package com.springbootaws.domain.config.validator;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberLoginService;
import com.springbootaws.web.login.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginValidator implements Validator {
    private final MemberLoginService memberLoginService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(LoginDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginDto loginDto = (LoginDto) target;
        Member login = memberLoginService.login(loginDto.getUsername(), loginDto.getPassword());
        if (login.getUsername() == null || login.getPassword() == null) {
            errors.reject("loginFail", "아이디 혹은 비밀번호가 맞지 않습니다.");
        }
        log.info("errors={}", errors.getFieldError());
        log.info("username={}", login.getUsername());
        log.info("password={}", login.getPassword());
    }
}
