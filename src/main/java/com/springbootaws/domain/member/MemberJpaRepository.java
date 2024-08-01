package com.springbootaws.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {
    @Query("select m from Member m where m.username= :username and m.password= :password")
    Optional<Member> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    boolean existsByUsername(String username);
}
