package com.solvedex.test.service;

import com.solvedex.test.conveter.PostConverter;
import com.solvedex.test.model.Post;
import com.solvedex.test.model.dto.NewPostDto;
import com.solvedex.test.model.dto.PostDto;
import com.solvedex.test.repository.PostRepository;
import com.solvedex.test.security.model.User;
import com.solvedex.test.security.service.UsserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostConverter postConverter;

    @Autowired
    private final UsserService usserService;

    public PostService(PostRepository postRepository, PostConverter postConverter, UsserService usserService) {
        this.postRepository = postRepository;
        this.postConverter = postConverter;
        this.usserService = usserService;
    }

    public PostDto getPost(Long id) {
        return postRepository.findById(id)
                .map(postConverter::entityToDto)
                .orElse(null);
    }


    public List<PostDto> getPosts() {
        return postRepository.findAllOrderedByCreationDateDesc().stream()
                .map(postConverter::entityToDto).collect(Collectors.toList());
    }

    public PostDto addPost(NewPostDto newPostDto) {

        Post newPost = Post.builder().title(newPostDto.getTitle()).content(newPostDto.getContent()).build();
        if (newPost.getCreationDate() == null) {
            newPost.setCreationDate(LocalDateTime.now());
        }

        User userAuthor = usserService.getCurrentUser();
        newPost.setUser(userAuthor);
        return postConverter.entityToDto(postRepository.save(newPost));
    }

    public PostDto updatePost(Long id, NewPostDto newPostDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            throw new EntityNotFoundException("Error: " + id);
        }

        User userAuthor = usserService.getCurrentUser();
        Post existingPost = optionalPost.get();
        if (existingPost.getUser().getId() != userAuthor.getId()) {
            throw new EntityNotFoundException("Error: " + id);
        }
        if (newPostDto.getTitle() != null) {
            existingPost.setTitle(newPostDto.getTitle());
        }
        if (newPostDto.getContent() != null) {
            existingPost.setContent(newPostDto.getContent());
        }
        postRepository.save(existingPost);

        return postConverter.entityToDto(existingPost);

    }

    public PostDto deletePost(Long id) {
        Optional<Post> postToDelete = postRepository.findById(id);
        if (postToDelete.isPresent()) {
            postToDelete.ifPresent(postRepository::delete);
            return postConverter.entityToDto(postToDelete.get());
        } else {
            throw new EntityNotFoundException("Error: " + id);
        }

    }

}
