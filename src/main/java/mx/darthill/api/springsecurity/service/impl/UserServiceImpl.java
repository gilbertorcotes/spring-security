package mx.darthill.api.springsecurity.service.impl;

import mx.darthill.api.springsecurity.dto.SaveUsuario;
import mx.darthill.api.springsecurity.exeption.InvalidPasswordException;
import mx.darthill.api.springsecurity.persistence.entity.Usuario;
import mx.darthill.api.springsecurity.persistence.repository.UserRepository;
import mx.darthill.api.springsecurity.persistence.util.Rol;
import mx.darthill.api.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario resiteredOneUsuario(SaveUsuario newUser) {

        validatePassword(newUser);

        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usuario.setName(newUser.getNombre());
        usuario.setUsername(newUser.getUsername());
        usuario.setRole(Rol.CUSTOMER);

        return userRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(SaveUsuario newUser) {
        if(!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords no coinciden");
        }
        if(!newUser.getPassword().equals(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords no coinciden");
        }
    }
}
