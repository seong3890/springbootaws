package com.springbootaws.domain.product;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootaws.domain.member.QMember;
import com.springbootaws.web.product.ProductDto;

import com.springbootaws.web.product.QProductDto;

import com.springbootaws.web.product.book.BookSearch;
import com.springbootaws.web.product.dto.ProductFindDto;
import com.springbootaws.web.product.dto.QProductFindDto;
import jakarta.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.springbootaws.domain.member.QMember.*;
import static com.springbootaws.domain.product.QBook.*;
import static com.springbootaws.domain.product.QProduct.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
@Transactional(readOnly = true)
public class ProductQuerydslRepository {
    private final EntityManager em;

    private JPAQueryFactory factory;

    public ProductQuerydslRepository(EntityManager em) {
        this.em = em;
        this.factory = new JPAQueryFactory(em);
    }

    // Product 단일 화면
    public ProductFindDto productFindDto(Long bookId) {
        ProductFindDto productFindDto = factory.select(new QProductFindDto(
                        book.id,
                        book.name, book.creator, book.price, book.quantity
                        , book.writer, book.isbn, member.nickname
                )).from(book)
                .join(book.member, member)
                .where(book.id.eq(bookId))
                .fetchOne();

        return productFindDto;
    }


    public Page<ProductDto> findAllPage(Pageable pageable, BookSearch bookSearch) {
        List<ProductDto> productDtos = factory.select(new QProductDto(
                        book.id, member.nickname, book.name, book.creator, book.price, book.quantity,
                        book.CreateDateTime, book.modifyDate
                )).from(book)
                .join(book.member, member)
                .where(nicknameEq(bookSearch.getNickname()),
                        writerEq(bookSearch.getWriter()),
                        nameEq(bookSearch.getName())
                ).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int size = factory.select(book)
                .from(book)
                .leftJoin(book.member, member).fetchJoin()
                .where(nicknameEq(bookSearch.getNickname()),
                        writerEq(bookSearch.getWriter()),
                        nameEq(bookSearch.getName())
                ).fetch().size();
        return new PageImpl<ProductDto>(productDtos, pageable, size);
    }


    private BooleanExpression nameEq(String name) {
        return hasText(name) ? book.name.eq(name) : null;
    }

    private BooleanExpression writerEq(String writer) {
        return hasText(writer) ? book.writer.eq(writer) : null;
    }

    private BooleanExpression nicknameEq(String nickname) {
        return hasText(nickname) ? member.nickname.eq(nickname) : null;
    }
}

