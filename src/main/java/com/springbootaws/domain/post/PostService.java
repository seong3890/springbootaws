package com.springbootaws.domain.post;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberJpaRepository;
import com.springbootaws.domain.post.query.PostQueryRepository;
import com.springbootaws.web.post.dto.PostDto;
import com.springbootaws.web.post.dto.PostSearch;
import com.springbootaws.web.post.dto.UpdatePostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostJpaRepository postJpaRepository;
    private final PostQueryRepository postQueryRepository;
    private final InquiryJpaRepository inquiryJpaRepository;
    private final MemberJpaRepository memberJpaRepository;


    public Page<PostDto> findPostListSearchPage(PostSearch search, Pageable pageable) {
        return postQueryRepository.PostSearchPage(search, pageable);
    }

    public PostDto findPost(Long postId) {
        Post post = postJpaRepository.findPost(postId);

        return new PostDto(post.getTitle(), post.getWrite(), post.getMember().getNickname(),
                post.getInquiry().getInquiry(), post.getCreateDateTime(), post.getModifyDate(),post.getId());
    }

    public List<Inquiry> findInquiry() {
        return inquiryJpaRepository.findAll();


    }

    @Transactional
    public Long update(Long id, UpdatePostDto postDto, Long inquiryId) {
        Inquiry inquiry = inquiryJpaRepository.findById(inquiryId).get();
        Post post = postJpaRepository.findById(postDto.getId()).orElseThrow(() -> new IllegalStateException("해당 글이 존재하지 않습니다."));
        post.updatePost(postDto.getTitle(), postDto.getWrite(), inquiry);
        return post.getId();
    }

    public void delete(Long id) {
        Post post = postJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
        postJpaRepository.delete(post);

    }

    public void create(PostDto postDto, Long inquiryId, Long memberId) {
        Inquiry inquiry = inquiryJpaRepository.findById(inquiryId).get();
        Member member = memberJpaRepository.findById(memberId).get();
        Post post = Post.builder()
                .member(member)
                .title(postDto.getTitle())
                .write(postDto.getWrite())
                .inquiry(inquiry)
                .build();

    }
}
