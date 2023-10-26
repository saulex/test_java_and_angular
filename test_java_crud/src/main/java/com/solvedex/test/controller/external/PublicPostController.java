package com.solvedex.test.controller.external;

import com.solvedex.test.model.dto.CommentDto;
import com.solvedex.test.model.dto.NewPostDto;
import com.solvedex.test.model.dto.PostDto;
import com.solvedex.test.service.CommentService;
import com.solvedex.test.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@CrossOrigin
public class PublicPostController {

    private final PostService postService;

    private final CommentService commentService;

    public PublicPostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping(value = "/post")
    public ResponseEntity<List<PostDto>> post() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping(value = "/post/{id}/comments")
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getCommentsForPost(id), HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<PostDto> addPost(@RequestBody NewPostDto newPost) {
        return new ResponseEntity<>(postService.addPost(newPost), HttpStatus.CREATED);
    }

}
