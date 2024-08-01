package com.springbootaws.web.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.springbootaws.web.product.dto.QProductFindDto is a Querydsl Projection type for ProductFindDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductFindDto extends ConstructorExpression<ProductFindDto> {

    private static final long serialVersionUID = -1407562020L;

    public QProductFindDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> creator, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<String> writer, com.querydsl.core.types.Expression<String> isbn, com.querydsl.core.types.Expression<String> nickname) {
        super(ProductFindDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, int.class, String.class, String.class, String.class}, id, name, creator, price, quantity, writer, isbn, nickname);
    }

}

