package com.springbootaws.web.product;

import com.springbootaws.domain.member.Member;

import com.springbootaws.domain.product.BookService;
import com.springbootaws.web.product.book.BookSearch;
import com.springbootaws.web.product.book.ProductInsertBookDto;
import com.springbootaws.web.product.dto.BookEditDto;
import com.springbootaws.web.product.dto.ProductFindDto;
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

import static com.springbootaws.SessionConst.LOGIN_MEMBER;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/book")
@Controller
public class BookController {
    private final BookService bookService;

    // 전체 조회
    @GetMapping
    public String products(@PageableDefault(size = 5, page = 0) Pageable pageable, Model model,@ModelAttribute("search") BookSearch bookSearch) {
        Page<ProductDto> productPage = bookService.findAll(pageable,bookSearch);
        model.addAttribute("productPage", productPage);
        return "product/book/products";
    }
    // 추가
    @GetMapping("/insert")
    public String insertForm(Model model, @SessionAttribute(name = LOGIN_MEMBER) Member member) {
        model.addAttribute("product", new ProductInsertBookDto());
        model.addAttribute("memberId", member.getId());

        return "product/book/insert";
    }
    // 추가
    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("product") ProductInsertBookDto product,
                         BindingResult bindingResult, @RequestParam(name = "memberId") Long id) {
        if (bindingResult.hasErrors()) {
            return "/product/book/insert";
        }
        bookService.create(id, product);
        return "redirect:/product/book";
    }
    // 단일 조회
    @GetMapping("/{bookId}")
    public String product(@PathVariable(name = "bookId") Long bookId, Model model) {
        ProductFindDto book = bookService.findBook(bookId);
        model.addAttribute("book", book);
        return "product/book/book";
    }

    // 수정
    @GetMapping("/{bookId}/edit")
    public String BooktEdit(@PathVariable(name = "bookId") Long bookId, Model model) {
        BookEditDto fetchBook = bookService.findFetchBook(bookId);
        model.addAttribute("book", fetchBook);
        return "product/book/bookEdit";
    }

    //수정
    @PostMapping("/{bookId}/edit")
    public String edit(@PathVariable(name = "bookId") Long bookId,
                       @Validated @ModelAttribute("book") BookEditDto bookEditDto,
                       BindingResult bindingResult,
                       RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "product/book/bookEdit";
        }
        bookService.editBook(bookId, bookEditDto);
        attributes.addAttribute("bookId", bookId);
        return "redirect:/product/book/{bookId}";
    }

    @DeleteMapping("/{bookId}/delete")
    @ResponseBody
    public Long deleteBook(@PathVariable(name = "bookId") Long bookId) {
        log.info("delete 진입");
        bookService.delete(bookId);
        return bookId;
    }
}
