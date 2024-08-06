package com.springbootaws.domain.post;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberJpaRepository;
import com.springbootaws.domain.post.query.PostQueryRepository;
import com.springbootaws.web.post.dto.*;
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


    public Page<PostListDto> findPostListSearchPage(PostSearch search, Pageable pageable) {
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
        log.info("id={}, post.id={}",id,postDto.getId());
        Post post = postJpaRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 글이 존재하지 않습니다."));
        post.updatePost(postDto.getTitle(), postDto.getWrite(), inquiry);
        return post.getId();
    }

    @Transactional
    public void delete(Long id) {
        Post post = postJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
        postJpaRepository.delete(post);
        log.info("삭제 성공!! id={}",id);

    }

    @Transactional
    public void create(PostInsertDto postInsertDto, Long inquiryId, Long memberId) {
        Inquiry inquiry = inquiryJpaRepository.findById(inquiryId).get();
        Member member = memberJpaRepository.findById(memberId).get();
        Post post = Post.builder()
                .member(member)
                .title(postInsertDto.getTitle())
                .write(postInsertDto.getWrite())
                .inquiry(inquiry)
                .build();
        postJpaRepository.save(post);

    }

    public UpdatePostDto finUpdatedPost(Long id) {
        Post post = postJpaRepository.findPost(id);

        return new UpdatePostDto(post.getId(),post.getTitle(),post.getMember().getNickname(), post.getWrite(),
                post.getInquiry().getInquiry(),post.getInquiry().getId());
    }
}
