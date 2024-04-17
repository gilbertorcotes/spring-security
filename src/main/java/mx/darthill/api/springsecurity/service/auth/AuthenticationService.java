package mx.darthill.api.springsecurity.service.auth;

import mx.darthill.api.springsecurity.dto.RegisterUser;
import mx.darthill.api.springsecurity.dto.SaveUsuario;
import mx.darthill.api.springsecurity.dto.auth.AuthenticationRequest;
import mx.darthill.api.springsecurity.dto.auth.AuthenticationResponse;
import mx.darthill.api.springsecurity.persistence.entity.Usuario;
import mx.darthill.api.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public RegisterUser registerOneUsuario(SaveUsuario newUser) {

        Usuario usuario = userService.resiteredOneUsuario(newUser);

        RegisterUser usuarioDto = new RegisterUser();

        usuarioDto.setId(usuario.getId());
        usuarioDto.setNombre(usuario.getName());
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setRol(usuario.getRole().name());

        String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));
        usuarioDto.setJwt(jwt);

        return usuarioDto;
    }

    private Map<String, Object> generateExtraClaims(Usuario usuario) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",usuario.getName());
        extraClaims.put("role", usuario.getRole());
        extraClaims.put("authorities",usuario.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findByUsername(authRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims((Usuario) user));

        AuthenticationResponse authResp = new AuthenticationResponse();
        authResp.setJwt(jwt);

        return authResp;
    }

    public boolean validateToken(String jwt) {

        try{
            jwtService.extractusername(jwt);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
