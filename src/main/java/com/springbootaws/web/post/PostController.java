package com.springbootaws.web.post;

import com.springbootaws.SessionConst;
import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.post.*;
import com.springbootaws.web.post.dto.PostDto;
import com.springbootaws.web.post.dto.PostSearch;
import com.springbootaws.web.post.dto.UpdatePostDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springbootaws.SessionConst.LOGIN_MEMBER;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping
    public String boardListSearchPage(@PageableDefault(size = 5, page = 0) Pageable pageable,
                                      @ModelAttribute("search") PostSearch search, Model model) {

        Page<PostDto> postListSearchPage = postService.findPostListSearchPage(search, pageable);
        model.addAttribute("post", postListSearchPage);
        return "post/list";
    }

    @GetMapping("/{postId}")
    public String postGet(@PathVariable("postId") Long postId, Model model) {
        PostDto postDto = postService.findPost(postId);
        model.addAttribute("post", postDto);
        return "/post";
    }

    @GetMapping("/{postId}/edit")
    public String updateForm(@PathVariable("postId") Long id, Model model) {
        PostDto postDto = postService.findPost(id);
        List<Inquiry> inquiryList = postService.findInquiry();
        model.addAttribute("post", postDto);
        model.addAttribute("inquiry", inquiryList);
        return "post/edit";
    }

    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable("postId") Long id, @Validated UpdatePostDto postDto,
                             BindingResult bindingResult, @RequestParam("inquiry") Long inquiry_id) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        Long PostsId = postService.update(id, postDto, inquiry_id);
        return "redirect:/board/" + PostsId;
    }

    @GetMapping("/insetPost")
    public String insertPostForm(@SessionAttribute(name = LOGIN_MEMBER) Member member, @ModelAttribute("post") PostDto postDto, Model model) {
        List<Inquiry> inquiry = postService.findInquiry();
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("nickname", member.getNickname());
        model.addAttribute("memberId", member.getId());
        return "post/insertPost";
    }

    @PostMapping("/insetPost")
    public String insertPost(@Validated PostDto postDto, BindingResult result,
                             @RequestParam("inquiryId") Long inquiry_id,
                             @RequestParam("memberId") Long memberId, Model model) {
        if (inquiry_id == null) {
            result.rejectValue("id", "range", "문의유형을 선택하세요");
        }
        if (result.hasErrors()) {
            return "board/insertBoard";
        }
        postService.create(postDto, inquiry_id, memberId);
        return "redirect:/board";
    }

    @DeleteMapping("/{boardId}/delete")
    @ResponseBody
    public Long deleteBoard(@PathVariable("boardId") Long id) {
        postService.delete(id);
        return id;
    }

}
