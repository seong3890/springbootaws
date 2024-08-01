package com.springbootaws.domain.member;

import com.springbootaws.domain.adress.Address;
import com.springbootaws.domain.post.Post;
import com.springbootaws.domain.product.Product;
import com.springbootaws.domain.time.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"nickname" ,"username", "address"})
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "member_seq", sequenceName = "MEMBER_SEQ", allocationSize = 1)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    List<Post> posts = new ArrayList<>();


    // protected로 막어놓아서 생성
    public static Member createEmptyMember() {
        return new Member();
    }
    @Builder
    public Member(String username, String password, String nickname, Address address) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }

    @Builder
    public Member(Long id, String username, String password, String nickname, Address address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }


}
