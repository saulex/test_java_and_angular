package com.solvedex.test.security.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

}
