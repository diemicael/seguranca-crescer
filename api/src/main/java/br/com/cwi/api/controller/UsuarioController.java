package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.EditarUsuarioRequest;
import br.com.cwi.api.controller.request.EnviarEmailRequest;
import br.com.cwi.api.controller.request.IncluirUsuarioRequest;
import br.com.cwi.api.controller.request.PasswordUpdateWithTokenRequest;
import br.com.cwi.api.controller.response.EditarUsuarioResponse;
import br.com.cwi.api.controller.response.UsuarioResponse;
import br.com.cwi.api.service.BuscarUsuarioService;
import br.com.cwi.api.service.EditarUsuarioService;
import br.com.cwi.api.service.IncluirUsuarioService;
import br.com.cwi.api.service.UsuarioPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private EditarUsuarioService editarUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioPasswordService usuarioPasswordService;

    @GetMapping("/me")
    @ResponseStatus(OK)
    public UsuarioResponse buscar() {
        return buscarUsuarioService.buscar();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UsuarioResponse incluir(@Valid @RequestBody IncluirUsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @PutMapping()
    @ResponseStatus(OK)
    public EditarUsuarioResponse editar(@Valid @RequestBody EditarUsuarioRequest request) {
        return editarUsuarioService.editar(request);
    }

    @PostMapping("/forgot-password/publico")
    public void forgotPassword(@RequestBody @Valid EnviarEmailRequest request) {
        usuarioPasswordService.enviarToken(request);
    }

    @PostMapping("/change-password/{token}/publico")
    public void changePassword(@Valid @RequestBody PasswordUpdateWithTokenRequest request, @PathVariable String token) {
        usuarioPasswordService.alterarSenha(request.getPassword(), token);
    }

}
