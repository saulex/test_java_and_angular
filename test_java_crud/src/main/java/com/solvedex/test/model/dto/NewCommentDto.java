package com.solvedex.test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.NamedEntityGraph;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph
public class NewCommentDto {
    private String content;

}