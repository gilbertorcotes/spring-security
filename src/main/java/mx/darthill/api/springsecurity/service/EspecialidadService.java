package mx.darthill.api.springsecurity.service;

import mx.darthill.api.springsecurity.dto.SaveEspecialidad;
import mx.darthill.api.springsecurity.persistence.entity.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface EspecialidadService {

    //@PreAuthorize("hasAuthority('READ_ALL_ESPECIALIDAD')") //Se fue a la implementación
    Page<Especialidad> findAll(Pageable pageable);

    Optional<Especialidad> findOneByID(Long especialidadId);

    Especialidad create(SaveEspecialidad saveEspecialidad);

    Especialidad updateById(Long especialidadID, SaveEspecialidad saveEspecialidad);

    Especialidad disableById(Long especialidadId);
}
