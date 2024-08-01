package com.springbootaws.web.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.springbootaws.domain.product.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookEditDto {
    private Long id;


    private String name;


    private String creator;

    private int price;

    private int quantity;
    private String writer;

    private String isbn;


    public BookEditDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.creator = book.getCreator();
        this.price = book.getPrice();
        this.quantity = book.getQuantity();
        this.writer = book.getWriter();
        this.isbn = book.getIsbn();

    }
}
