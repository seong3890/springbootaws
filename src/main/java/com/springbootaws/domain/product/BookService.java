package com.springbootaws.domain.product;

import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberJpaRepository;
import com.springbootaws.web.product.ProductDto;
import com.springbootaws.web.product.book.BookSearch;
import com.springbootaws.web.product.book.ProductInsertBookDto;
import com.springbootaws.web.product.dto.BookEditDto;
import com.springbootaws.web.product.dto.ProductFindDto;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {
    private final ProductJpaRepository productJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final ProductQuerydslRepository querydslRepository;

    public Page<ProductDto> findAll(Pageable pageable, BookSearch bookSearch) {
//        Page<Book> products = productJpaRepository.findAllPage(pageable);
//        return products.map(product -> new ProductDto(product));
        Page<ProductDto> productFindDtos = querydslRepository.findAllPage(pageable, bookSearch);
        return productFindDtos;
    }

    @Transactional
    public void create(Long id, ProductInsertBookDto product) {
        Member member = memberJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        Book book = Book.builder()
                .writer(product.getWriter())
                .isbn(product.getIsbn())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .member(member)
                .build();


        productJpaRepository.save(book);
    }

    public ProductFindDto findBook(Long BookId) {
        return querydslRepository.productFindDto(BookId);

    }


    public BookEditDto findFetchBook(Long productId) {
        Book book = (Book) productJpaRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
        return new BookEditDto(book);


    }

    @Transactional
    public void editBook(Long bookId, BookEditDto bookEditDto) {
        Book findBook =(Book) productJpaRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("해당 상품이 없습니다."));
        findBook.editBook(bookEditDto);

    }

    @Transactional
    public void delete(Long bookId) {
        Book book = productJpaRepository.findBook(bookId)
                .orElseThrow(()->new IllegalStateException("해당 상품이 존재하지 않습니다."));
        productJpaRepository.delete(book);
    }

    public List<ProductDto> BookTop10() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Book> top10 = productJpaRepository.findTop10(pageable);
       return top10.stream().map(book -> new ProductDto(book.getId(),book.getMember().getNickname(),book.getName(),
               book.getCreator(),
               book.getPrice(),book.getQuantity(),book.getCreateDateTime(), book.getModifyDate())).toList();

    }
}
