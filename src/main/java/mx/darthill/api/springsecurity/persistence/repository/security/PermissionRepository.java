package mx.darthill.api.springsecurity.persistence.repository.security;

import mx.darthill.api.springsecurity.persistence.entity.security.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<GrantedPermission, Long> {
}
