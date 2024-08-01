package com.springbootaws.domain.config.validator;

import com.springbootaws.domain.member.MemberJpaRepository;
import com.springbootaws.web.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberValidator implements Validator {
    private final MemberJpaRepository memberJpaRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MemberDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDto memberDto = (MemberDto) target;
        if (memberJpaRepository.existsByUsername(memberDto.getUsername())) {
            errors.rejectValue("username", "name", "사용중인 아이디입니다.");
        }
    }
}
