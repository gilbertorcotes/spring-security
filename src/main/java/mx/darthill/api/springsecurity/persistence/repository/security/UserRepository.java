package mx.darthill.api.springsecurity.persistence.repository.security;

import mx.darthill.api.springsecurity.persistence.entity.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
