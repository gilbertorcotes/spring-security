package mx.darthill.api.springsecurity.persistence.entity;

import jakarta.persistence.*;

@Entity
public class Veterinarios {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private VeterinariosStatus status;

    public static enum VeterinariosStatus{
        ENABLED,DISABLED;
    }

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

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

    public VeterinariosStatus getStatus() {
        return status;
    }

    public void setStatus(VeterinariosStatus status) {
        this.status = status;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
