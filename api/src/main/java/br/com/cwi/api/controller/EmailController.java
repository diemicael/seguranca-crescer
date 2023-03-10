package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.EnviarEmailRequest;
import br.com.cwi.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/send-email")
public class EmailController {

    @Autowired
    private EmailService emailService;

//    @PostMapping
//    @ResponseStatus(OK)
//    public void enviarEmail(@Valid @RequestBody EnviarEmailRequest request) {
//        emailService.enviarEmail(request);
//    }
}
