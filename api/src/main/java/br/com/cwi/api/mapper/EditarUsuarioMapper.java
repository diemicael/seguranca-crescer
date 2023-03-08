package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.EditarUsuarioResponse;
import br.com.cwi.api.domain.Usuario;

public class EditarUsuarioMapper {
    public static EditarUsuarioResponse toResponse(Usuario entity) {
        return EditarUsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .atualizadoEm(entity.getAtualizadoEm())
                .foto(entity.getFoto())
                .build();
    }
}
