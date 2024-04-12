package mx.darthill.api.springsecurity.persistence.repository;

import mx.darthill.api.springsecurity.persistence.entity.Veterinarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinariosRepository extends JpaRepository<Veterinarios, Long> {
}
