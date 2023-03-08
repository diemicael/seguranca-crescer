package br.com.cwi.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IncluirUsuarioRequest {

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
    @NotBlank(message = "Telefone é obrigatório.")
    private String telefone;
    @Email(message = "Preencha um email válido.")
    @NotNull(message = "Email é obrigatório.")
    private String email;
    @NotBlank(message = "Senha é obrigátio.")
    private String senha;
    private String foto;
}
