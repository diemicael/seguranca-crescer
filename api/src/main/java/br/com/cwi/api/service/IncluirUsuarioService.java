package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirUsuarioRequest;
import br.com.cwi.api.controller.response.UsuarioResponse;
import br.com.cwi.api.domain.Usuario;
import br.com.cwi.api.repository.UsuarioRepository;
import br.com.cwi.api.security.domain.Permissao;
import br.com.cwi.api.service.core.NowService;
import br.com.cwi.api.service.core.ValidaEmailUnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.mapper.UsuarioMapper.toEntity;
import static br.com.cwi.api.mapper.UsuarioMapper.toResponse;
import static br.com.cwi.api.security.domain.Funcao.USUARIO;
import static br.com.cwi.api.service.core.NowService.*;

@Service
public class IncluirUsuarioService {

    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponse incluir(IncluirUsuarioRequest request) {

        validaEmailUnicoService.validar(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setCriadoEm(getDateTime());
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.adicionarPermissao(getPermissaoPadrao());
        usuario.setAtivo(true);

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

    private Permissao getPermissaoPadrao() {
        Permissao permissao = new Permissao();
        permissao.setFuncao(USUARIO);
        return permissao;
    }
}
