package mx.darthill.api.springsecurity.service.impl;

import mx.darthill.api.springsecurity.dto.SaveUsuario;
import mx.darthill.api.springsecurity.exception.InvalidPasswordException;
import mx.darthill.api.springsecurity.exception.ObjectNotFoundException;
import mx.darthill.api.springsecurity.persistence.entity.security.Role;
import mx.darthill.api.springsecurity.persistence.entity.security.Usuario;
import mx.darthill.api.springsecurity.persistence.repository.security.UserRepository;
import mx.darthill.api.springsecurity.service.RoleService;
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

    @Autowired
    private RoleService roleService;

    @Override
    public Usuario resiteredOneUsuario(SaveUsuario newUser) {

        validatePassword(newUser);

        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usuario.setName(newUser.getNombre());
        usuario.setUsername(newUser.getUsername());

        Role defaultRole = roleService.findDefaultRole()
                .orElseThrow(() -> new ObjectNotFoundException("Rol no encontrado"));
        usuario.setRole(defaultRole);
//        usuario.setRole(RolEnum.CUSTOMER);   //Se utiliza con Emuneraciones

        return userRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(SaveUsuario newUser) {

        System.out.println("Entro a Validate");

        if(!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords no coinciden en esta vacio " + StringUtils.hasText(newUser.getPassword()));
        }
        if(!newUser.getPassword().equals(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords no coinciden no corresponden");
        }
    }
}
