package com.solvedex.test.controller.internal;
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
import static org.mockito.Mockito.when;

public class PostControllerTest {

	@Mock
	private PostService postService;

	@Mock
	private CommentService commentService;

	@InjectMocks
	private PostController postController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnCreatedPost() {
		// Given
		NewPostDto newPost = new NewPostDto();
		PostDto post = new PostDto();
		when(postService.addPost(newPost)).thenReturn(post);

		// When
		ResponseEntity<PostDto> responseEntity = postController.addPost(newPost);

		// Then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isEqualTo(post);
	}

	@Test
	public void shouldThrowExceptionWhenNewPostIsNull() {
		// Given
		NewPostDto newPost = null;

		// When
		try {
			postController.addPost(newPost);
		} catch (Exception e) {
			// Then
			assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
		}
	}

	@Test
	public void shouldReturnListOfPosts() {
		// Given
		List<PostDto> posts = new ArrayList<>();
		posts.add(new PostDto());
		when(postService.getPosts()).thenReturn(posts);

		// When
		ResponseEntity<List<PostDto>> responseEntity = postController.getPosts();

		// Then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo(posts);
	}

	@Test
	public void shouldReturnPostById() {
		// Given
		Long postId = 1L;
		PostDto post = new PostDto();
		when(postService.getPost(postId)).thenReturn(post);

		// When
		ResponseEntity<PostDto> responseEntity = postController.getPost(postId);

		// Then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo(post);
	}

}
