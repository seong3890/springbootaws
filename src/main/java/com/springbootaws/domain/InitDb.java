package com.springbootaws.domain;

import com.springbootaws.domain.adress.Address;
import com.springbootaws.domain.member.Member;
import com.springbootaws.domain.member.MemberJpaRepository;
import com.springbootaws.domain.post.Inquiry;
import com.springbootaws.domain.post.InquiryJpaRepository;
import com.springbootaws.domain.post.Post;
import com.springbootaws.domain.post.PostJpaRepository;
import com.springbootaws.domain.product.Book;
import com.springbootaws.domain.product.BookService;
import com.springbootaws.domain.product.Product;
import com.springbootaws.domain.product.ProductJpaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Profile("dev")
@RequiredArgsConstructor
@Component
public class InitDb {

    private final HelloDb helloDb;
  //  @PostConstruct
    public void postConstruct() {
        helloDb.init1();
        helloDb.init2();
    }

    @Slf4j
    @RequiredArgsConstructor
    @Transactional
    @Component
    static class HelloDb {
        private final EntityManager em;
        private final MemberJpaRepository memberJpaRepository;
        private final InquiryJpaRepository inquiryJpaRepository;
        private final ProductJpaRepository productJpaRepository;
        private final PostJpaRepository postJpaRepository;
        public void init1() {
            Member member = new Member("admin", "admin123", "유어드민", new Address("경기도", "100번지"));
            memberJpaRepository.save(member);

            Inquiry inquiry1 = new Inquiry("상품문의");
            Inquiry inquiry2 = new Inquiry("기타문의");
            Inquiry inquiry3 = new Inquiry("배송문의");
            Inquiry inquiry4 = new Inquiry("탈퇴문의");
            inquiryJpaRepository.save(inquiry1);
            inquiryJpaRepository.save(inquiry2);
            inquiryJpaRepository.save(inquiry3);
            inquiryJpaRepository.save(inquiry4);






            }

        public void init2() {
            Member member1 = memberJpaRepository.findById(1L).orElseThrow(() -> new RuntimeException("Member not found"));
            Inquiry inquiry = inquiryJpaRepository.findById(1L).orElseThrow(() -> new RuntimeException("Inquiry not found"));

            for (int i = 0; i < 10; i++) {
                Product book = Book.builder()
                        .writer("스프링고수")
                        .isbn("1234"+i)
                        .name("스프링Book"+i)
                        .creator("스프링책판매자")
                        .price(10000)
                        .quantity(100)
                        .member(member1)
                        .build();

//                Post post = createPost("테스트용글" + i, "테스트글" + i , member1, inquiry);
                Post post = new Post("테스트용글" + i, "테스트글" + i , member1, inquiry);

                log.info("post.getInquiry={}, Inquiry.getId={}", post.getInquiry().getId(), inquiry.getId());
                productJpaRepository.save(book);
                postJpaRepository.save(post);
        }
    }

        private Post createPost(String title, String write, Member member, Inquiry inquiry) {
          /*  Post post = Post.builder()
                    .title(title)
                    .write(write)
                    .member(member)
                    .inquiry(inquiry)
                    .build();*/
            Post post = new Post(title, write, member, inquiry);
            return post;
        }
    }
}
