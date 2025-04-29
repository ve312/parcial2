package ve312.com.materiasCrud.Service;

import ve312.com.materiasCrud.domain.Asignatura;

import java.util.List;

public interface IasignaturaService {
    public List<Asignatura> listar();
    public Asignatura buscar(Long id);
    public void eliminar(Long id);
    public void guardar(Asignatura asignatura);
}
