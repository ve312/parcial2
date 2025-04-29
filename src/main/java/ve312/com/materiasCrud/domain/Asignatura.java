package ve312.com.materiasCrud.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "asignatura")
@Data
@Schema(description = "Entidad que representa una asignatura en el sistema")
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asi_id")
    @Schema(description = "Identificador único de la asignatura", example = "1")
    private Long id;

    @NotEmpty
    @Column(name = "asi_nombre")
    @Schema(description = "Nombre de la asignatura", example = "Matemáticas")
    private String nombre;

    @NotEmpty
    @Column(name = "asi_descripcion")
    @Schema(description = "Descripción de la asignatura", example = "Curso introductorio de matemáticas.")
    private String Descripcion;

    @Min(0)
    @NotNull
    @Column(name = "asi_salon")
    @Schema(description = "Número del salón donde se dicta la asignatura", example = "101")
    private int salon;

    @NotNull
    @Column(name = "asi_horaInicio")
    @Schema(description = "Hora de inicio de la asignatura", example = "08:00:00")
    private LocalTime horaInicio;

    @NotNull
    @Column(name = "asi_horaFin")
    @Schema(description = "Hora de finalización de la asignatura", example = "10:00:00")
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    @Schema(description = "Profesor que imparte la asignatura")
    private Profesor profesor;

}
