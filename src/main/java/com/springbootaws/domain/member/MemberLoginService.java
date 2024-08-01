package com.springbootaws.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberLoginService {
    private final MemberJpaRepository memberJpaRepository;

    public Member login(String username, String password) {
        log.info("service username={}", username);
        log.info("service password={}", password);
       Optional<Member> loginMember =  memberJpaRepository.findByUsernameAndPassword(username, password);
        if (loginMember.isEmpty()) {
            return Member.createEmptyMember();
        }
        return loginMember.get();
    }
}
