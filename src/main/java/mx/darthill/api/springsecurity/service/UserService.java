package mx.darthill.api.springsecurity.service;

import mx.darthill.api.springsecurity.dto.SaveUsuario;
import mx.darthill.api.springsecurity.persistence.entity.Usuario;

public interface UserService {
    Usuario resiteredOneUsuario(SaveUsuario newUser);
}
