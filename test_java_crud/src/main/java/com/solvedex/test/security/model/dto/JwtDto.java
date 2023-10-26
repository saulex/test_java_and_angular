package com.solvedex.test.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String bearer = "Bearer";

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private Collection<? extends GrantedAuthority> authorities;

    @Getter
    @Setter
    private Integer idUser;

    public JwtDto(String token, String userName, Integer idUser, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userName = userName;
        this.authorities = authorities;
        this.idUser = idUser;
    }
}
