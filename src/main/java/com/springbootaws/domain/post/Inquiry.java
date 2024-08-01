package com.springbootaws.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;




@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry {
    @Id
    @SequenceGenerator(name = "inquiry_seq", sequenceName = "INQUIRY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inquiry;

    public Inquiry(String inquiry) {
        this.inquiry = inquiry;
    }


}
