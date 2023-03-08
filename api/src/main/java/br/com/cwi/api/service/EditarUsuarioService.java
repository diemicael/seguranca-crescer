package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.EditarUsuarioRequest;
import br.com.cwi.api.controller.response.EditarUsuarioResponse;
import br.com.cwi.api.domain.Usuario;
import br.com.cwi.api.mapper.EditarUsuarioMapper;
import br.com.cwi.api.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.mapper.EditarUsuarioMapper.*;
import static br.com.cwi.api.service.core.NowService.*;

@Service
public class EditarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public EditarUsuarioResponse editar(EditarUsuarioRequest request) {

        Usuario usuario = usuarioAutenticadoService.get();
        usuario.setNome(request.getNome());
        usuario.setTelefone(request.getTelefone());
        usuario.setFoto(request.getFoto());
        usuario.setAtualizadoEm(getDateTime());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
