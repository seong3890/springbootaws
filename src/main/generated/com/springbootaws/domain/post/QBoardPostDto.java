package com.springbootaws.domain.post;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.springbootaws.domain.post.QBoardPostDto is a Querydsl Projection type for BoardPostDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardPostDto extends ConstructorExpression<BoardPostDto> {

    private static final long serialVersionUID = -330212294L;

    public QBoardPostDto(com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<java.time.LocalDateTime> createDate, com.querydsl.core.types.Expression<Long> id) {
        super(BoardPostDto.class, new Class<?>[]{String.class, String.class, java.time.LocalDateTime.class, long.class}, title, nickname, createDate, id);
    }

}

