package com.springbootaws.web.product;

import com.querydsl.core.annotations.QueryProjection;
import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String nickname;
    private String name;
    private String creator;
    private int price;
    private int quantity;
    private LocalDateTime localCreateTime;
    private LocalDateTime localModifyTime;
    private String createTime;
    private String modifyTime;

    public ProductDto(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    // 상품 전체 목록

    @QueryProjection
    public ProductDto(Long id, String nickname, String name, String creator, int price, int quantity, LocalDateTime localCreateTime, LocalDateTime localModifyTime) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.creator = creator;
        this.price = price;
        this.quantity = quantity;
        this.createTime = localCreateTime.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
        this.modifyTime = localModifyTime.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
    }


}



