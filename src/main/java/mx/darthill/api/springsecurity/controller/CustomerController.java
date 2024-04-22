package mx.darthill.api.springsecurity.controller;

import mx.darthill.api.springsecurity.dto.RegisterUser;
import mx.darthill.api.springsecurity.dto.SaveUsuario;
import mx.darthill.api.springsecurity.persistence.entity.security.Usuario;
import mx.darthill.api.springsecurity.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<RegisterUser> registerOne(@RequestBody @Valid SaveUsuario newUser){
        RegisterUser registeredUser = authenticationService.registerOneUsuario(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PreAuthorize("denyAll")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(Arrays.asList());
    }

}
