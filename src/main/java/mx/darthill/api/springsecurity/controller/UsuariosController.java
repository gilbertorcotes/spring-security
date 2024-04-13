package mx.darthill.api.springsecurity.controller;

import jakarta.validation.Valid;
import mx.darthill.api.springsecurity.dto.RegisterUser;
import mx.darthill.api.springsecurity.service.auth.AuthenticationService;
import mx.darthill.api.springsecurity.dto.SaveUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<RegisterUser> registerOne(@RequestBody @Valid SaveUsuario newUser){
        RegisterUser registeredUser = authenticationService.registerOneUsuario(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
