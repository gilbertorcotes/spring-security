package mx.darthill.api.springsecurity.service;

import mx.darthill.api.springsecurity.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
}
