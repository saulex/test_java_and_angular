package com.solvedex.test.repository;


import com.solvedex.test.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIdPost(Long id);

}
