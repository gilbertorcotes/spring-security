package mx.darthill.api.springsecurity.service.auth;

import mx.darthill.api.springsecurity.dto.RegisterUser;
import mx.darthill.api.springsecurity.dto.SaveUsuario;
import mx.darthill.api.springsecurity.persistence.entity.Usuario;
import mx.darthill.api.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

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
}
