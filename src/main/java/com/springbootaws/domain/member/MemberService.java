package com.springbootaws.domain.member;

import com.springbootaws.domain.adress.Address;
import com.springbootaws.domain.product.ProductJpaRepository;
import com.springbootaws.web.member.dto.MemberDto;
import com.springbootaws.web.member.dto.MemberNicknameDto;
import com.springbootaws.web.product.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public List<MemberNicknameDto> findMemberDto() {
        List<Member> members = memberJpaRepository.findAll();
        return members.stream().map(member -> new MemberNicknameDto(member.getId(),member.getNickname())).toList();

    }



    public Long signUp(MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .address(new Address(memberDto.getCity(), memberDto.getAddress()))
                .build();
        memberJpaRepository.save(member);
        return member.getId();
    }
}
