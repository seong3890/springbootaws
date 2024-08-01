package com.springbootaws.web.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.springbootaws.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class ProductFindDto {
    private Long id;
    private String name;


    private String creator;

    private int price;

    private int quantity;
    private String writer;

    private String isbn;
    private String nickname;

    @QueryProjection
    public ProductFindDto(Long id, String name, String creator, int price, int quantity, String writer, String isbn, String nickname) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.price = price;
        this.quantity = quantity;
        this.writer = writer;
        this.isbn = isbn;
        this.nickname = nickname;
    }
}
