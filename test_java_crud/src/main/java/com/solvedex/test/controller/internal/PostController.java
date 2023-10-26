package com.solvedex.test.controller.internal;


import com.solvedex.test.model.dto.CommentDto;
import com.solvedex.test.model.dto.NewCommentDto;
import com.solvedex.test.model.dto.NewPostDto;
import com.solvedex.test.model.dto.PostDto;
import com.solvedex.test.service.CommentService;
import com.solvedex.test.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin

public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/")
    public ResponseEntity<PostDto> addPost(@RequestBody NewPostDto newPost) {
        return new ResponseEntity<>(postService.addPost(newPost), HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<PostDto>> getPosts() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getCommentsForPost(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<CommentDto> addPostComments(@PathVariable Long id, @RequestBody NewCommentDto newCommentDto) {
        return new ResponseEntity<>(commentService.addComment(id, newCommentDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody NewPostDto newPost) {
        return new ResponseEntity<>(postService.updatePost(id, newPost), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }

}
