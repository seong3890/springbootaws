package com.springbootaws.domain.post;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.time.BaseTimeEntity;
import com.springbootaws.web.post.dto.PostDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "post_seq", sequenceName = "POST_SEQ", allocationSize = 1)
    private Long id;

    private String title;
    @Column(name = "writer")
    private String write;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    //생성 메서드
    public static Post createPost(PostDto postDto, Member member, Inquiry inquiry) {

        Post post = Post.builder()
                .member(member)
                .title(postDto.getTitle())
                .write(postDto.getWrite())
                .inquiry(inquiry)
                .build();
        return post;
    }

    @Builder
    public Post(String title, String write, Member member, Inquiry inquiry) {
        this.title = title;
        this.write = write;
        this.member = member;
        this.inquiry = inquiry;
    }

    public void updatePost(String title, String write, Inquiry inquiry) {
        this.title = title;
        this.write = write;
        this.inquiry = inquiry;
    }
}
