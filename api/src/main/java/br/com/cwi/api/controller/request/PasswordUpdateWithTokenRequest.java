package br.com.cwi.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordUpdateWithTokenRequest {

    @NotBlank
    private String password;
}
