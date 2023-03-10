package br.com.cwi.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnviarEmailRequest {

    @Email(message = "Preencha um email válido.")
    @NotNull(message = "Email é obrigatório.")
    private String email;
}
