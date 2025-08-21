package com.sport.utilisateur_api.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String app;
}
