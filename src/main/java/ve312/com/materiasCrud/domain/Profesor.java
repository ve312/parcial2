package ve312.com.materiasCrud.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "profesor")
@Data
@Schema(description = "Entidad que representa a un profesor en el sistema")
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    @Schema(description = "Identificador único del profesor", example = "1")
    private Long id;

    @NotEmpty
    @Column(name= "pro_nombre")
    @Schema(description = "Nombre del profesor", example = "Juan")
    private String nombre;

    @OneToMany(mappedBy = "profesor" , cascade = CascadeType.ALL)
    @Schema(description = "Lista de asignaturas que imparte el profesor")
    private List<Asignatura> asignaturas;

    @OneToOne
    @JoinColumn(name = "usu_id")
    @Schema(description = "Usuario del sistema asociado al profesor")
    private Usuario usuario;

}
