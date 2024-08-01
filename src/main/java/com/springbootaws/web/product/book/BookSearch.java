package com.springbootaws.web.product.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearch {
    String nickname;
    String writer;
    String name;
}


