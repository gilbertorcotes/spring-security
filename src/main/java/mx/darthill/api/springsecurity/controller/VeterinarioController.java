package mx.darthill.api.springsecurity.controller;

import jakarta.validation.Valid;
import mx.darthill.api.springsecurity.dto.SaveVeterinario;
import mx.darthill.api.springsecurity.persistence.entity.Veterinarios;
import mx.darthill.api.springsecurity.service.VeterinariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinariosService veterinariosService;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    @GetMapping
    public ResponseEntity<Page<Veterinarios>> findAll(Pageable pageable){
        Page<Veterinarios> veterinariosPage = veterinariosService.findAll(pageable);

        if (veterinariosPage.hasContent()){
            return ResponseEntity.ok(veterinariosPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{veterinariosId}")
    public ResponseEntity<Veterinarios> findOneById(@PathVariable Long veterinariosId){

        Optional<Veterinarios> veterinario = veterinariosService.findOneByID(veterinariosId);

        if (veterinario.isPresent()){
            return ResponseEntity.ok(veterinario.get());
        }

        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping
    public ResponseEntity<Veterinarios> create(@RequestBody @Valid SaveVeterinario saveVeterinario){

        Veterinarios veterinarios = veterinariosService.create(saveVeterinario);
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarios);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'OPERATOR')")
    @PutMapping("/{veterinariosId}")
    public ResponseEntity<Veterinarios> updateById(@PathVariable Long veterinariosId ,
                                                   @RequestBody @Valid SaveVeterinario saveVeterinario){

        Veterinarios veterinarios = veterinariosService.updateById(veterinariosId, saveVeterinario);
        return ResponseEntity.ok(veterinarios);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/{veterinariosId}/disabled")
    public ResponseEntity<Veterinarios> disableById(@PathVariable Long veterinariosId){

        Veterinarios veterinarios = veterinariosService.disableById(veterinariosId);
        return ResponseEntity.ok(veterinarios);
    }


}
