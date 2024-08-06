package com.springbootaws.web.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data

public class PostListDto {
    private Long id;
    private String nickname;
    private String inquiry;
    private String title;
    private LocalDateTime localCreateTime;
    private String createTime;

    public PostListDto(Long id, String nickname, String inquiry, String title, LocalDateTime localCreateTime) {
        this.id = id;
        this.nickname = nickname;
        this.inquiry = inquiry;
        this.title = title;
        this.createTime = localCreateTime.format(DateTimeFormatter.ofPattern("dd:HH"));
    }
}
