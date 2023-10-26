package com.solvedex.test.repository;


import com.solvedex.test.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
   @Query("SELECT p FROM Post p ORDER BY p.creationDate DESC")
    List<Post> findAllOrderedByCreationDateDesc();
}