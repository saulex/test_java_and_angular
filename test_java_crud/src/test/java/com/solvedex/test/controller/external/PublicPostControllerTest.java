package com.solvedex.test.controller.external;


import com.solvedex.test.model.dto.CommentDto;
import com.solvedex.test.model.dto.NewPostDto;
import com.solvedex.test.model.dto.PostDto;
import com.solvedex.test.service.CommentService;
import com.solvedex.test.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PublicPostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private PublicPostController publicPostController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Pruebas para el método post()

    @Test
    public void post_shouldReturnListOfPosts() {
        // Given
        List<PostDto> posts = new ArrayList<>();
        posts.add(new PostDto());
        when(postService.getPosts()).thenReturn(posts);

        // When
        ResponseEntity<List<PostDto>> responseEntity = publicPostController.post();

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(posts);

        // Verify that the `postService.getPosts()` method was called
        verify(postService).getPosts();
    }

    // Pruebas para el método getPostComments()

    @Test
    public void getPostComments_shouldReturnListOfCommentsForPost() {
        // Given
        Long postId = 1L;
        List<CommentDto> comments = new ArrayList<>();
        comments.add(new CommentDto());
        when(commentService.getCommentsForPost(postId)).thenReturn(comments);

        // When
        ResponseEntity<List<CommentDto>> responseEntity = publicPostController.getPostComments(postId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(comments);

        // Verify that the `commentService.getCommentsForPost()` method was called with the correct parameters
        verify(commentService).getCommentsForPost(postId);
    }

    // Pruebas para el método addPost()

    @Test
    public void addPost_shouldReturnCreatedPost() {
        // Given
        NewPostDto newPost = new NewPostDto();
        PostDto post = new PostDto();
        when(postService.addPost(newPost)).thenReturn(post);

        // When
        ResponseEntity<PostDto> responseEntity = publicPostController.addPost(newPost);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(post);

        // Verify that the `postService.addPost()` method was called with the correct parameters
        verify(postService).addPost(newPost);
    }

}
