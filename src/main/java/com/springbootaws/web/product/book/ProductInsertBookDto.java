package com.springbootaws.web.product.book;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class ProductInsertBookDto {
    @NotBlank
    private String name;

    @NotBlank
    private String creator;
    @Range(max = 1000000,min = 1000)
    private int price;
    @Range(max = 1000,min = 100)
    private int quantity;
    @NotBlank
    private String writer;
    @NotBlank
    private String isbn;

    // 상품 추가, 조회, 수정용 dto
    @QueryProjection
    public ProductInsertBookDto(String name, String creator, int price, int quantity, String writer, String isbn) {
        this.name = name;
        this.creator = creator;
        this.price = price;
        this.quantity = quantity;
        this.writer = writer;
        this.isbn = isbn;
    }
}

