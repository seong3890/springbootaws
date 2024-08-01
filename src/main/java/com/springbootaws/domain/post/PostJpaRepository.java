package com.springbootaws.domain.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"member","inquiry"})
    @Query("select p from Post p where p.id= :postId")
    Post findPost(@Param("postId") Long postId);
}
