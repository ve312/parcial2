package ve312.com.materiasCrud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ve312.com.materiasCrud.dao.IasignaturaDao;
import ve312.com.materiasCrud.domain.Asignatura;

import java.util.List;

@Service
public class AsignaturaService implements IasignaturaService{
    @Autowired
    private IasignaturaDao asignaturaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> listar() {
        return asignaturaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Asignatura buscar(Long id) {
        var asignatura = asignaturaDao.findById(id).orElse(null);
        return asignatura;
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        asignaturaDao.deleteById(id);
    }

    @Override
    @Transactional
    public void guardar(Asignatura asignatura) {
        asignaturaDao.save(asignatura);
    }
}
