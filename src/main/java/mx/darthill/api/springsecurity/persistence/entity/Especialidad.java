package mx.darthill.api.springsecurity.persistence.entity;

import jakarta.persistence.*;

@Entity
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private EspecialidadStatus status;

    public static enum EspecialidadStatus{
        ENABLED, DISABLED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EspecialidadStatus getStatus() {
        return status;
    }

    public void setStatus(EspecialidadStatus status) {
        this.status = status;
    }
}
