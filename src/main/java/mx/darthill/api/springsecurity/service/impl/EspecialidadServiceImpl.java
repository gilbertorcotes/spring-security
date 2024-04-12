package mx.darthill.api.springsecurity.service.impl;

import mx.darthill.api.springsecurity.dto.SaveEspecialidad;
import mx.darthill.api.springsecurity.exeption.ObjectNotFoundException;
import mx.darthill.api.springsecurity.persistence.entity.Especialidad;
import mx.darthill.api.springsecurity.persistence.repository.EspecialidadRepository;
import mx.darthill.api.springsecurity.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Override
    public Page<Especialidad> findAll(Pageable pageable) {
        return especialidadRepository.findAll(pageable);
    }

    @Override
    public Optional<Especialidad> findOneByID(Long especialidadId) {
        return especialidadRepository.findById(especialidadId);
    }

    @Override
    public Especialidad create(SaveEspecialidad saveEspecialidad) {

        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(saveEspecialidad.getNombre());

        return especialidadRepository.save(especialidad);
    }

    @Override
    public Especialidad updateById(Long especialidadId, SaveEspecialidad saveEspecialidad) {
        Especialidad especialidadFromDB = especialidadRepository.findById(especialidadId)
                        .orElseThrow( () -> new ObjectNotFoundException("Especialidad no encontrada con ID : "  + especialidadId));

        especialidadFromDB.setNombre(saveEspecialidad.getNombre());
        return especialidadRepository.save(especialidadFromDB);
    }

    @Override
    public Especialidad disableById(Long especialidadId) {
        Especialidad especialidadFromDB = especialidadRepository.findById(especialidadId)
                .orElseThrow( () -> new ObjectNotFoundException("Especialidad no encontrada con ID : "  + especialidadId));

        return especialidadRepository.save(especialidadFromDB);
    }
}
