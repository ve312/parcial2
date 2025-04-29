package ve312.com.materiasCrud.Service;

import ve312.com.materiasCrud.domain.Profesor;

import java.util.List;

public interface IprofesorService {
    public List<Profesor> listar();
    public Profesor buscar(Long id);
    public void eliminar(Long id);
    public void guardar(Profesor profesor);
}
