package com.springbootaws.web.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSearch {
    private String nickname;
    private String title;
}
