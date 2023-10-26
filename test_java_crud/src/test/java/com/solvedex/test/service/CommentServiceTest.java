package com.solvedex.test.service;

import com.solvedex.test.model.Comment;
import com.solvedex.test.model.Post;
import com.solvedex.test.model.dto.CommentDto;
import com.solvedex.test.repository.CommentsRepository;
import com.solvedex.test.repository.PostRepository;
import com.solvedex.test.security.model.User;
import com.solvedex.test.model.dto.NewCommentDto;
import com.solvedex.test.security.service.UsserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class CommentServiceTest {

	@InjectMocks
	private CommentService commentService;

	@Mock
	private PostRepository postRepository;

	@Mock
	private CommentsRepository commentsRepository;

	@Mock
	private UsserService usserService;

	@BeforeEach
	public void setup() {
		// Configurar el comportamiento de los mocks si es necesario
	}

	@Test
	public void testGetCommentsForPost() {
		Long postId = 1L;
		Post post = new Post();
		post.setId(postId);

		Comment comment1 = new Comment("Comment 1", new User(), postId, LocalDateTime.now());
		Comment comment2 = new Comment("Comment 2", new User(), postId, LocalDateTime.now());

		Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
		List<Comment> value = new ArrayList<>();
		value.add(comment1);
		value.add(comment2);
		Mockito.when(commentsRepository.findByIdPost(postId)).thenReturn(value);

		List<CommentDto> comments = commentService.getCommentsForPost(postId);


		assertThat(comments).isNotNull();
		assertThat(comments).hasSize(2);
		assertThat(comments.get(0).getComment()).isEqualTo("Comment 1");
		assertThat(comments.get(1).getComment()).isEqualTo("Comment 2");
	}

	@Test
	public void testAddComment() {
		Long postId = 1L;
		NewCommentDto newCommentDto = new NewCommentDto();
		newCommentDto.setContent("New Comment");

		Post post = new Post();
		post.setId(postId);

		User user = new User();
		// Configura el usuario actual si es necesario

		Comment comment = new Comment(newCommentDto.getContent(), user, postId, LocalDateTime.now());

		Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
		Mockito.when(usserService.getCurrentUser()).thenReturn(user);
		Mockito.when(commentsRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

		CommentDto addedComment = commentService.addComment(postId, newCommentDto);

		assertThat(addedComment).isNotNull();
		assertThat(addedComment.getComment()).isEqualTo("New Comment");
		// Agrega más comprobaciones según sea necesario
	}

	@Test
	public void testAddCommentPostNotFound() {
		Long postId = 1L;
		NewCommentDto newCommentDto = new NewCommentDto();
		newCommentDto.setContent("New Comment");

		Mockito.when(postRepository.findById(postId)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> commentService.addComment(postId, newCommentDto))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("No blog post found for postId: " + postId);
	}

	// Puedes escribir más pruebas para otros métodos según sea necesario

}