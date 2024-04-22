package mx.darthill.api.springsecurity.persistence.repository.security;

import mx.darthill.api.springsecurity.persistence.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String defaultRole);
}
