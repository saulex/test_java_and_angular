package com.solvedex.test.service;

import com.solvedex.test.model.Post;
import com.solvedex.test.model.dto.NewPostDto;
import com.solvedex.test.model.dto.PostDto;
import com.solvedex.test.repository.PostRepository;
import com.solvedex.test.conveter.PostConverter;
import com.solvedex.test.security.model.User;
import com.solvedex.test.security.service.UsserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SpringBootTest
public class PostServiceTest {

	@InjectMocks
	private PostService postService;

	@Mock
	private PostRepository postRepository;

	@Mock
	private PostConverter postConverter;

	@Mock
	private UsserService usserService;



	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetPostWhenPostExists() {
		// Arrange
		Long postId = 1L;
		Post post = new Post();
		post.setId(postId);
		PostDto postDto = new PostDto();
		postDto.setId(postId);

		Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
		Mockito.when(postConverter.entityToDto(post)).thenReturn(postDto);

		// Act
		PostDto result = postService.getPost(postId);

		// Assert
		assertNotNull(result);
		assertEquals(postId, result.getId());
	}

	@Test
	public void testGetPostWhenPostDoesNotExist() {
		// Arrange
		Long postId = 1L;

		Mockito.when(postRepository.findById(postId)).thenReturn(Optional.empty());

		// Act
		PostDto result = postService.getPost(postId);

		// Assert
		assertNull(result);
	}

	@Test
	public void testAddPost() {
		// Arrange
		NewPostDto newPostDto = NewPostDto.builder().title("Title").content("Content").build();
		Post newPost = new Post();
		newPost.setTitle(newPostDto.getTitle());
		newPost.setContent(newPostDto.getContent());
		PostDto postDto = new PostDto();
		postDto.setId(1L);

		User user = new User();
		user.setId(1);
		Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(newPost);
		Mockito.when(postConverter.entityToDto(newPost)).thenReturn(postDto);
		Mockito.when(usserService.getCurrentUser()).thenReturn(user);

		// Act
		PostDto result = postService.addPost(newPostDto);

		// Assert
		assertNotNull(result);
		assertEquals(Optional.of(1L).get(), result.getId());
	}
}
