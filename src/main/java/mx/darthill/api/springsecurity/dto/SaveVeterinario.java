package mx.darthill.api.springsecurity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class SaveVeterinario implements Serializable {

    @NotBlank (message = "El nombre bo puede ir en blanco")
    private String Nombre;

    private Long especialidadId;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Min(value = 1)
    public Long getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }
}
