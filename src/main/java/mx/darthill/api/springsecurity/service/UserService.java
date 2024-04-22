package mx.darthill.api.springsecurity.service;

import mx.darthill.api.springsecurity.dto.SaveUsuario;
import mx.darthill.api.springsecurity.persistence.entity.security.Usuario;

import java.util.Optional;

public interface UserService {
    Usuario resiteredOneUsuario(SaveUsuario newUser);

    Optional<Usuario> findByUsername(String username);
}
