package com.springbootaws.domain.post.query;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootaws.domain.member.QMember;
import com.springbootaws.domain.post.QInquiry;
import com.springbootaws.domain.post.QPost;
import com.springbootaws.web.post.dto.PostDto;
import com.springbootaws.web.post.dto.PostListDto;
import com.springbootaws.web.post.dto.PostSearch;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.springbootaws.domain.member.QMember.member;
import static com.springbootaws.domain.post.QInquiry.inquiry1;
import static com.springbootaws.domain.post.QPost.post;
import static org.springframework.util.StringUtils.*;

@Slf4j
@Transactional(readOnly = true)
@Repository
public class PostQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory factory;

    public PostQueryRepository(EntityManager em) {
        this.em = em;
        this.factory = new JPAQueryFactory(em);
    }


    public Page<PostListDto> PostSearchPage(PostSearch search, Pageable pageable) {
        List<PostListDto> fetch = factory.select(Projections.constructor(PostListDto.class,
                        post.id, member.nickname, inquiry1.inquiry, post.title,
                        post.CreateDateTime ))
                .from(post)
                .join(post.member, member)
                .join(post.inquiry,inquiry1)
                .where(nicknameEq(search.getNickname()),
                        titleEq(search.getTitle()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int size = factory.selectFrom(post)
                .join(post.member, member).fetchJoin()
                .join(post.inquiry,inquiry1).fetchJoin()
                .where(nicknameEq(search.getNickname()),
                        titleEq(search.getTitle()))
                .fetch().size();
        return new PageImpl<>(fetch, pageable, size);
    }

    private BooleanExpression titleEq(String title) {
        return hasText(title) ? post.title.eq(title) : null;
    }

    private BooleanExpression nicknameEq(String nickname) {
        return hasText(nickname) ? member.nickname.eq(nickname) : null;
    }

}
