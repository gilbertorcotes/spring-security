package mx.darthill.api.springsecurity.controller;

import jakarta.validation.Valid;
import mx.darthill.api.springsecurity.dto.SaveEspecialidad;
import mx.darthill.api.springsecurity.dto.SaveVeterinario;
import mx.darthill.api.springsecurity.persistence.entity.Especialidad;
import mx.darthill.api.springsecurity.persistence.entity.Veterinarios;
import mx.darthill.api.springsecurity.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

//    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
//    @PreAuthorize("hasAuthority('READ_ALL_ESPECIALIDAD')") // La autorización se fue al servicio
    @GetMapping
    public ResponseEntity<Page<Especialidad>> findAll(Pageable pageable){
        Page<Especialidad> especialidadPage = especialidadService.findAll(pageable);

        if (especialidadPage.hasContent()){
            return ResponseEntity.ok(especialidadPage);
        }

        return ResponseEntity.notFound().build();
    }

//    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    @PreAuthorize("hasAuthority('READ_ONE_ESPECIALIDAD')")
    @GetMapping("/{especialidadId}")
    public ResponseEntity<Especialidad> findOneById(@PathVariable Long especialidadId){

        Optional<Especialidad> especialidad = especialidadService.findOneByID(especialidadId);

        if (especialidad.isPresent()){
            return ResponseEntity.ok(especialidad.get());
        }

        return ResponseEntity.notFound().build();
    }

//    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PreAuthorize("hasAuthority('UPDATE_ONE_ESPECIALIDAD')")
    @PostMapping
    public ResponseEntity<Especialidad> create(@RequestBody @Valid SaveEspecialidad saveEspecialidad){
        Especialidad especialidad = especialidadService.create(saveEspecialidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidad);
    }
//    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    @PreAuthorize("hasAuthority('UPDATE_ESPECIALIDAD')")
    @PutMapping("/{especialidadId}")
    public ResponseEntity<Especialidad> updateById(@PathVariable Long especialidadID,
                                                   @RequestBody @Valid SaveEspecialidad saveEspecialidad){

        Especialidad especialidad = especialidadService.updateById(especialidadID, saveEspecialidad);
        return ResponseEntity.ok(especialidad);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    @PutMapping("/{especialidadId}/disabled")
    public ResponseEntity<Especialidad> disableById(@PathVariable Long especialidadId){

        Especialidad especialidad = especialidadService.disableById(especialidadId);
        return ResponseEntity.ok(especialidad);
    }


}
