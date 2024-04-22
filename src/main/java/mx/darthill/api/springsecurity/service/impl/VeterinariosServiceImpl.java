package mx.darthill.api.springsecurity.service.impl;

import mx.darthill.api.springsecurity.dto.SaveVeterinario;
import mx.darthill.api.springsecurity.exception.ObjectNotFoundException;
import mx.darthill.api.springsecurity.persistence.entity.Especialidad;
import mx.darthill.api.springsecurity.persistence.entity.Veterinarios;
import mx.darthill.api.springsecurity.persistence.repository.VeterinariosRepository;
import mx.darthill.api.springsecurity.service.VeterinariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeterinariosServiceImpl implements VeterinariosService {

    @Autowired
    private VeterinariosRepository veterinariosRepository;

    @Override
    public Page<Veterinarios> findAll(Pageable pageable) {
        return veterinariosRepository.findAll(pageable);
    }

    @Override
    public Optional<Veterinarios> findOneByID(Long veterinariosId) {
        return veterinariosRepository.findById(veterinariosId);
    }

    @Override
    public Veterinarios create(SaveVeterinario saveVeterinario) {

        Veterinarios veterinarios = new Veterinarios();
        veterinarios.setNombre(saveVeterinario.getNombre());
        veterinarios.setStatus(Veterinarios.VeterinariosStatus.ENABLED);

        Especialidad especialidad = new Especialidad();
        especialidad.setId(saveVeterinario.getEspecialidadId());
        veterinarios.setEspecialidad(especialidad);

        return veterinariosRepository.save(veterinarios);
    }

    @Override
    public Veterinarios updateById(Long veterinariosId, SaveVeterinario saveVeterinario) {

        Veterinarios veterinariosFromDB = veterinariosRepository.findById(veterinariosId)
                        .orElseThrow( () -> new ObjectNotFoundException("Veterinario no encontrado con el ID: " + veterinariosId) );
        veterinariosFromDB.setNombre(saveVeterinario.getNombre());

        Especialidad especialidad = new Especialidad();
        especialidad.setId(saveVeterinario.getEspecialidadId());
        veterinariosFromDB.setEspecialidad(especialidad);

        return veterinariosRepository.save(veterinariosFromDB);
    }

    @Override
    public Veterinarios disableById(Long veterinariosId) {
        Veterinarios veterinariosFromDB = veterinariosRepository.findById(veterinariosId)
                .orElseThrow( () -> new ObjectNotFoundException("Veterinario no encontrado con el ID: " + veterinariosId) );
        veterinariosFromDB.setStatus(Veterinarios.VeterinariosStatus.DISABLED);
        return veterinariosRepository.save(veterinariosFromDB);
    }
}
