package com.springbootaws.web.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Slf4j
public class PostDto {
    @NotBlank
    private String title;

    private String write;

    private String nickname;

    private String inquiry;

    private String createTime;

    private String modifyTime;

    private Long id;



    public PostDto(String title, String write, String nickname, String inquiry, LocalDateTime createTime, LocalDateTime modifyTime, Long id) {
        this.title = title;
        this.write = write;
        this.nickname = nickname;
        this.inquiry = inquiry;
        this.createTime = createTime.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:SS"));
        this.modifyTime = modifyTime.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:SS"));
        this.id = id;
    }
}
