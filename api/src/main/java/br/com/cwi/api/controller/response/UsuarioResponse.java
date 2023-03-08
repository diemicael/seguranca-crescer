package br.com.cwi.api.controller.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime criadoEm;
    private String foto;
}
