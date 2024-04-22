package mx.darthill.api.springsecurity.service.impl;

import mx.darthill.api.springsecurity.persistence.entity.security.Role;
import mx.darthill.api.springsecurity.persistence.repository.security.RoleRepository;
import mx.darthill.api.springsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Value("${security.default.role}")
    private String defaultRole;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }
}
