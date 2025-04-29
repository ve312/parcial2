package ve312.com.materiasCrud;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ve312.com.materiasCrud.Service.IasignaturaService;
import ve312.com.materiasCrud.Service.IprofesorService;
import ve312.com.materiasCrud.dao.IusuarioDao;
import ve312.com.materiasCrud.domain.Asignatura;
import ve312.com.materiasCrud.domain.Profesor;
import ve312.com.materiasCrud.domain.Usuario;

@Controller
@Slf4j
@Tag(name = "Controlador de asignaturas", description = "Controlador para manejar las asignaturas y horarios en la aplicación")
public class Controlador {
    @Autowired
    private IasignaturaService asignaturaDao;
    @Autowired
    private IprofesorService profesorDao;
    @Autowired
    private IusuarioDao usuarioDao;

    @Operation(summary = "Página de login", description = "Acceso a la página de inicio de sesión")
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Operation(summary = "Página de error 403", description = "Muestra la página de error 403 cuando el usuario no tiene permisos para acceder a una sección")
    @GetMapping("/errores/403")
    public String error403(Model model) {
        return "errores/403";
    }

    @Operation(summary = "Página de inicio", description = "Muestra la página principal de la aplicación")
    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @Operation(summary = "Ver lista de asignaturas", description = "Muestra la lista de todas las asignaturas disponibles")
    @GetMapping("/ver")
    public String ver(Model model) {
        var listaAsignaturas = asignaturaDao.listar();
        model.addAttribute("asignaturas", listaAsignaturas);
        return "listaAsignaturas";
    }

    @Operation(summary = "Crear asignatura", description = "Muestra el formulario para crear una nueva asignatura")
    @GetMapping("/crear")
    public String crear(Asignatura asignatura, Model model) {
        return "crear";
    }

    @Operation(summary = "Guardar asignatura", description = "Guarda una nueva asignatura después de validarla")
    @PostMapping("/guardar")
    public String guardar(Model model, @Valid Asignatura asignatura, Errors errors, Profesor profesor) {
        if (errors.hasErrors()) {
            return "crear";
        }
        asignaturaDao.guardar(asignatura);
        asignatura.setProfesor(null);

        var profesores = profesorDao.listar();
        model.addAttribute("profesores", profesores);
        model.addAttribute("id", asignatura.getId());

        return "seleccionarProfesor";
    }

    @Operation(summary = "Guardar profesor de la asignatura", description = "Asigna un profesor a una asignatura")
    @PostMapping("/guardar/profesor")
    public String guardarProfesor(@RequestParam(required = false) Long profesorId, Model model,@RequestParam(required = false) Long id) {
        var profesor = profesorDao.buscar(profesorId);
        var asignatura = asignaturaDao.buscar(id);

        asignatura.setProfesor(profesor);

        asignaturaDao.guardar(asignatura);

        var listaAsignaturas = asignaturaDao.listar();
        model.addAttribute("asignaturas", listaAsignaturas);

        return "listaAsignaturas";
    }

    @Operation(summary = "Editar horarios de asignatura", description = "Muestra el formulario para editar los horarios de una asignatura si se es el profesor a cargo de la misma")
    @GetMapping("/editar/horarios/{id}")
    public String editarHorarios(Model model, @PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuariolog = usuarioDao.findByUsername(userDetails.getUsername());

        if(asignaturaDao.buscar(id).getProfesor().getUsuario().getId().equals(usuariolog.getId())) {
            model.addAttribute("asignatura", asignaturaDao.buscar(id));
            return "editarHorarios";
        }

        return "errores/403";
    }

    @Operation(summary = "Guardar horarios de asignatura", description = "Guarda los nuevos horarios de la asignatura después de ser modificados")
    @PostMapping("/subir")
    public String guardarHorarios(Model model, @ModelAttribute Asignatura asignatura, Errors errors) {

        /*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuariolog = usuarioDao.findByUsername(userDetails.getUsername());
         */
        var asignaturaActu = asignaturaDao.buscar(asignatura.getId());
        asignaturaActu.setHoraInicio(asignatura.getHoraInicio());
        asignaturaActu.setHoraFin(asignatura.getHoraFin());

        asignaturaDao.guardar(asignaturaActu);

        return "redirect:/ver";
    }

    @Operation(summary = "Editar asignatura", description = "Muestra el formulario para editar los detalles de una asignatura existente")
    @GetMapping("/modificar/{id}")
    public String editar(Model model, @PathVariable Long id) {
        var asignatura = asignaturaDao.buscar(id);
        model.addAttribute("asignatura", asignatura);
        return "crear";
    }

    @Operation(summary = "Eliminar asignatura", description = "Elimina una asignatura de la base de datos")
    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable Long id) {
        asignaturaDao.eliminar(id);
        return "redirect:/ver";
    }


}
