package com.springbootaws.domain.product;

import com.springbootaws.web.product.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    @Query(value = "select distinct b from Book b join fetch b.member m",
    countQuery = "select count(b) from Book b")
    Page<Book> findAllPage(Pageable pageable);


    @Query("select b from Book b where b.id= :bookId")
    Optional<Book> findBook(@Param("bookId") Long bookId);

    @EntityGraph(attributePaths = {"member"})
    @Query("select b from Book b  order by b.id desc ")
    List<Book> findTop10(Pageable pageable);

}
