package mx.darthill.api.springsecurity.service;

import mx.darthill.api.springsecurity.dto.SaveVeterinario;
import mx.darthill.api.springsecurity.persistence.entity.Veterinarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VeterinariosService {
    Page<Veterinarios> findAll(Pageable pageable);

    Optional<Veterinarios> findOneByID(Long veterinariosId);

    Veterinarios create(SaveVeterinario saveVeterinario);

    Veterinarios updateById(Long veterinariosId, SaveVeterinario saveVeterinario);

     Veterinarios disableById(Long veterinariosId);
}
