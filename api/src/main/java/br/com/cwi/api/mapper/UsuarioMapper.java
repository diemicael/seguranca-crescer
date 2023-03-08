package br.com.cwi.api.mapper;


import br.com.cwi.api.controller.request.IncluirUsuarioRequest;
import br.com.cwi.api.controller.response.UsuarioResponse;
import br.com.cwi.api.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(IncluirUsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setTelefone(request.getTelefone());
        entity.setEmail(request.getEmail());
        entity.setFoto(request.getFoto());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setTelefone(entity.getTelefone());
        response.setEmail(entity.getEmail());
        response.setCriadoEm(entity.getCriadoEm());
        response.setFoto(entity.getFoto());
        return response;
    }
}
