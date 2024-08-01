package com.springbootaws.web.product;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.springbootaws.web.product.QProductDto is a Querydsl Projection type for ProductDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductDto extends ConstructorExpression<ProductDto> {

    private static final long serialVersionUID = -1836645914L;

    public QProductDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> creator, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<java.time.LocalDateTime> localCreateTime, com.querydsl.core.types.Expression<java.time.LocalDateTime> localModifyTime) {
        super(ProductDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, int.class, int.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class}, id, nickname, name, creator, price, quantity, localCreateTime, localModifyTime);
    }

}

