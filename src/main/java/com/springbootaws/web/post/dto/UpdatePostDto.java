package com.springbootaws.web.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePostDto {
    private Long id;
    @NotBlank
    private String title;

    private String write;

    private String nickname;

    private String inquiry;

    private Long inquiryId;

    public UpdatePostDto(Long id, String title, String write, String nickname, String inquiry, Long inquiryId) {
        this.id = id;
        this.title = title;
        this.write = write;
        this.nickname = nickname;
        this.inquiry = inquiry;
        this.inquiryId = inquiryId;
    }
}
