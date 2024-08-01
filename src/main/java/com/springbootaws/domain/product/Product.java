package com.springbootaws.domain.product;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.time.BaseTimeEntity;
import com.springbootaws.web.product.dto.BookEditDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public abstract class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String creator;
    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    // 상품의 수량을 늘린다. 구매 취소 할 때 다시 추가하는 메소드
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    // 구매한 수량만큼 재고 수를 줄인다.
    public void removeQuantity(int quantity) {
        if (this.quantity - quantity < 0) {
            throw new RuntimeException("수량이 부족합니다.");
        }
        this.quantity = this.quantity - quantity;
    }


    public Product(String name, String creator, int price, int quantity, Member member) {
        this.name = name;
        this.creator = creator;
        this.price = price;
        this.quantity = quantity;
        this.member = member;
    }

    void updateProductFields(BookEditDto bookEditDto) {
        this.name = bookEditDto.getName();
        this.creator = bookEditDto.getCreator();
        this.price = bookEditDto.getPrice();
        this.quantity = bookEditDto.getQuantity();
    }
}
