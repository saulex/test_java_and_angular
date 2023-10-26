package com.solvedex.test.conveter;

import com.solvedex.test.model.Comment;
import com.solvedex.test.model.dto.CommentDto;
import com.solvedex.test.security.model.User;
import org.springframework.stereotype.Component;

@Component
public class ConverterComment {

    public static CommentDto entityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());
        commentDto.setAuthor(comment.getUser().getName());
        commentDto.setCreationDate(comment.getCreationDate());
        commentDto.setIdPost(comment.getIdPost());
        commentDto.setIdUser(comment.getUser().getId());
        return commentDto;
    }

}