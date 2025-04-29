package ve312.com.materiasCrud.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="rol")
@Data
@Schema(description = "Entidad que representa un rol dentro del sistema")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    @Schema(description = "Identificador único del rol", example = "1")
    private Long id;

    @NotEmpty
    @Column(name = "rol_nombre")
    @Schema(description = "Nombre del rol", example = "ADMIN")
    private String nombre;

    @ManyToMany(mappedBy = "roles")
    @Schema(description = "Lista de usuarios asociados a este rol")
    private List<Usuario> usuarios;

}
