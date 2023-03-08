package br.com.cwi.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EditarUsuarioRequest {

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
    @NotBlank(message = "Telefone é obrigatório.")
    private String telefone;
    private String foto;
}
