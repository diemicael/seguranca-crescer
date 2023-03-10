package br.com.cwi.api.service.core;

import br.com.cwi.api.domain.Usuario;
import br.com.cwi.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class EncontrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY, "Email não encontrado"));
    }
}
