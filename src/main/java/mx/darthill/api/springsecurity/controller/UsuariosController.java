package mx.darthill.api.springsecurity.controller;

import jakarta.validation.Valid;
import mx.darthill.api.springsecurity.dto.RegisterUser;
import mx.darthill.api.springsecurity.persistence.entity.Usuario;
import mx.darthill.api.springsecurity.service.auth.AuthenticationService;
import mx.darthill.api.springsecurity.dto.SaveUsuario;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")  //Se utiliza despues de desvincular los matchers
    @PostMapping
    public ResponseEntity<RegisterUser> registerOne(@RequestBody @Valid SaveUsuario newUser){
        RegisterUser registeredUser = authenticationService.registerOneUsuario(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

//    @PreAuthorize("denyAll")  //Se utiliza despues de desvincular los matchers
//    @GetMapping
//    public ResponseEntity<List<Usuario>> findAll(){
//        return ResponseEntity.ok(Arrays.asList());
//    }
}
