package com.solvedex.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solvedex.test.security.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    public Comment(String content, User user, Long idPost, LocalDateTime creationDate) {
        this.comment = content;
        this.user = user;
        this.idPost = idPost;
        this.creationDate = creationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String comment;
    private LocalDateTime creationDate;
    private Long idPost;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "email", "userName"})
    private User user;


}
