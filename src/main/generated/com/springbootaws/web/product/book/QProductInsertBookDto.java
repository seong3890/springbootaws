package com.springbootaws.web.product.book;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.springbootaws.web.product.book.QProductInsertBookDto is a Querydsl Projection type for ProductInsertBookDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductInsertBookDto extends ConstructorExpression<ProductInsertBookDto> {

    private static final long serialVersionUID = -1804747651L;

    public QProductInsertBookDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> creator, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<String> writer, com.querydsl.core.types.Expression<String> isbn) {
        super(ProductInsertBookDto.class, new Class<?>[]{String.class, String.class, int.class, int.class, String.class, String.class}, name, creator, price, quantity, writer, isbn);
    }

}

