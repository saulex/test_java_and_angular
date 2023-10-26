package com.solvedex.test.service;


import com.solvedex.test.conveter.ConverterComment;
import com.solvedex.test.model.Comment;
import com.solvedex.test.model.Post;
import com.solvedex.test.model.dto.CommentDto;
import com.solvedex.test.model.dto.NewCommentDto;
import com.solvedex.test.repository.CommentsRepository;
import com.solvedex.test.repository.PostRepository;
import com.solvedex.test.security.model.User;
import com.solvedex.test.security.service.UsserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final CommentsRepository commentsRepository;

    @Autowired
    private final UsserService usserService;


    private final ConverterComment converterComment;

    public CommentService(PostRepository postRepository, CommentsRepository commentsRepository, UsserService usserService, ConverterComment converterComment) {
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
        this.usserService = usserService;
        this.converterComment = converterComment;
    }

    public List<CommentDto> getCommentsForPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent())
            return new ArrayList<>();

        return commentsRepository.findByIdPost(postId).stream()
                .map(comment -> converterComment.entityToDto(comment)).collect(Collectors.toList());
    }

    public CommentDto addComment(Long postId, NewCommentDto newCommentDto) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent())
            throw new IllegalArgumentException("No blog post found for postId: " + postId);
        User userAuthor = usserService.getCurrentUser();
        return ConverterComment.entityToDto(
                commentsRepository.save(
                        new Comment(
                                newCommentDto.getContent(),
                                userAuthor,
                                postId,
                                LocalDateTime.now()
                        )
                )
        );

    }
}
