package com.solvedex.test.conveter;

import com.solvedex.test.model.Post;
import com.solvedex.test.model.dto.PostDto;
import com.solvedex.test.security.model.User;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public PostDto entityToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setCreationDate(post.getCreationDate());
        postDto.setAuthorName(post.getUser().getName());
        postDto.setIdUser(post.getUser().getId());
        return postDto;
    }

}
