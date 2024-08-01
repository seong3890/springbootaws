package com.springbootaws.domain.product;

import com.springbootaws.domain.member.Member;
import com.springbootaws.web.product.dto.BookEditDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@DiscriminatorValue("book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Book extends Product{
    private String writer;
    private String isbn;



    @Builder
    public Book(String writer, String isbn, String name, String creator, int price, int quantity, Member member) {
        super(name,creator,price,quantity,member);
        this.writer = writer;
        this.isbn = isbn;
    }


    public void editBook(BookEditDto bookEditDto) {

        super.updateProductFields(bookEditDto);
        this.writer = bookEditDto.getWriter();
        this.isbn = bookEditDto.getIsbn();
    }




}
