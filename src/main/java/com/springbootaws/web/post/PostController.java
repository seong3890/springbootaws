package com.springbootaws.web.post;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberService;
import com.springbootaws.domain.post.*;
import com.springbootaws.web.post.dto.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.springbootaws.SessionConst.LOGIN_MEMBER;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping
    public String boardListSearchPage(@PageableDefault(size = 5, page = 0) Pageable pageable,
                                      @ModelAttribute("search") PostSearch search, Model model) {

        Page<PostListDto> postListSearchPage = postService.findPostListSearchPage(search, pageable);
        model.addAttribute("posts", postListSearchPage);
        return "post/postList";
    }

    @GetMapping("/{postId}")
    public String postGet(@PathVariable("postId") Long postId, Model model) {
        PostDto postDto = postService.findPost(postId);
        model.addAttribute("post", postDto);
        return "post/post";
    }

    @GetMapping("/{postId}/edit")
    public String updateForm(@PathVariable("postId") Long id, Model model) {
        UpdatePostDto postDto = postService.finUpdatedPost(id);
        List<Inquiry> inquiryList = postService.findInquiry();
        model.addAttribute("post", postDto);
        model.addAttribute("inquiry", inquiryList);
        return "post/edit";
    }

    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable("postId") Long id, @Validated @ModelAttribute("post") UpdatePostDto postDto,
                             BindingResult bindingResult, @RequestParam("inquiryId") Long inquiry_id) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        Long PostsId = postService.update(id, postDto, inquiry_id);
        return "redirect:/post/" + PostsId;
    }

    @GetMapping("/insertPost")
    public String insertPostForm(@SessionAttribute(name = LOGIN_MEMBER) Member member,
                                 @ModelAttribute("post") PostInsertDto postInsertDto, Model model) {
        List<Inquiry> inquirys = postService.findInquiry();
        model.addAttribute("inquirys", inquirys);
        model.addAttribute("nickname", member.getNickname());
        model.addAttribute("memberId", member.getId());
        return "post/insertPost";
    }

    @PostMapping("/insertPost")
    public String insertPost(@Validated PostInsertDto postInsertDto, BindingResult result,
                             @RequestParam("inquiryId") Long inquiry_id,
                             @RequestParam("memberId") Long memberId) {
        if (inquiry_id == null) {
            result.rejectValue("id", "range", "문의유형을 선택하세요");
        }
        if (result.hasErrors()) {
            return "post/insertPost";
        }
        postService.create(postInsertDto, inquiry_id, memberId);
        return "redirect:/post";
    }

    @ResponseBody
    @DeleteMapping("/{postId}/delete")
    public Long deleteBoard(@PathVariable("postId") Long id) {

            postService.delete(id);
            log.info("id={} 삭제 성공", id);

        return id;
    }

}
