package mx.darthill.api.springsecurity.controller;

import jakarta.validation.Valid;
import mx.darthill.api.springsecurity.dto.auth.AuthenticationRequest;
import mx.darthill.api.springsecurity.dto.auth.AuthenticationResponse;
import mx.darthill.api.springsecurity.persistence.entity.Usuario;
import mx.darthill.api.springsecurity.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")  //Se utiliza despues de desvincular los matchers
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){

        boolean isTokenValid = authenticationService.validateToken(jwt);

        return ResponseEntity.ok(isTokenValid);

    }

    @PreAuthorize("permitAll")  //Se utiliza despues de desvincular los matchers
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){

            AuthenticationResponse rsp = authenticationService.login(authenticationRequest);

        return ResponseEntity.ok(rsp);
    }

//    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR', 'CUSTOMER')")
    @PreAuthorize("hasAuthority('READ_PROFILE')")
    @GetMapping("/profile")
    public ResponseEntity<Usuario> findMyProfile(){
        Usuario usuario = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(usuario);
    }
}
