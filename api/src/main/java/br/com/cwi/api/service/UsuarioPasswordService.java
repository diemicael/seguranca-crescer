package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.EnviarEmailRequest;
import br.com.cwi.api.domain.PasswordToken;
import br.com.cwi.api.domain.Usuario;
import br.com.cwi.api.repository.UsuarioRepository;
import br.com.cwi.api.service.core.EncontrarUsuarioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UsuarioPasswordService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncontrarUsuarioService encontrarUsuarioService;

    @Autowired
    private EmailService emailService;

    @SneakyThrows
    public void enviarToken(EnviarEmailRequest request) {
        Usuario usuario = encontrarUsuarioService.buscarUsuario(request.getEmail());

        KeyBasedPersistenceTokenService tokenService = getInstanceFor(usuario);

        Token token = tokenService.allocateToken(usuario.getEmail());

        emailService.enviarEmail(request.getEmail(), "Recuperação de senha", "Você poderá redefinir a senha acessando o link: http://localhost:3000/change-password/" + token.getKey());
    }

    @SneakyThrows
    @Transactional
    public void alterarSenha(String novaSenha, String token) {
        PasswordToken publicData = readPublicData(token);

        if(isExpired(publicData)) {
            throw new ResponseStatusException(BAD_REQUEST, "Token expirado");
        }

        Usuario usuario = encontrarUsuarioService.buscarUsuario(publicData.getEmail());

        KeyBasedPersistenceTokenService tokenService = this.getInstanceFor(usuario);
        tokenService.verifyToken(token);

        usuario.setSenha(this.passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }

    private boolean isExpired(PasswordToken publicData) {
        Instant createdAt = new Date(publicData.getCriadoEm()).toInstant();
        Instant now = new Date().toInstant();
        return createdAt.plus(Duration.ofMinutes(5)).isBefore(now);
    }

    private KeyBasedPersistenceTokenService getInstanceFor(Usuario usuario) throws Exception {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();

        tokenService.setServerSecret(usuario.getSenha());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }

    private PasswordToken readPublicData(String token) {
        String tokenDecoded = new String(Base64.getDecoder().decode(token));
        String[] tokenParts = tokenDecoded.split(":");
        Long timestamp = Long.parseLong(tokenParts[0]);
        String email = tokenParts[2];
        return new PasswordToken(email, timestamp);
    }
}