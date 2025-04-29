package ve312.com.materiasCrud.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@Schema(description = "Entidad que representa un usuario del sistema")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;

    @NotEmpty
    @Column(name="usu_nombre")
    @Schema(description = "Nombre de usuario (utilizado para iniciar sesión)", example = "admin123")
    private String username;

    @NotEmpty
    @Column(name = "usu_password")
    @Schema(description = "Contraseña encriptada del usuario", example = "$2a$10$...")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usu_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    @Schema(description = "Lista de roles asociados al usuario")
    private List<Rol> roles;

    /*
    @OneToOne(mappedBy = "usuario")
    private Profesor profesor;
     */

}
