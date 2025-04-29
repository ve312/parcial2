package ve312.com.materiasCrud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ve312.com.materiasCrud.dao.IprofesorDao;
import ve312.com.materiasCrud.domain.Profesor;

import java.util.List;

@Service
public class ProfesorService implements IprofesorService{
    @Autowired
    private IprofesorDao profesorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> listar() {
        return profesorDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor buscar(Long id) {
        var profesor = profesorDao.findById(id).orElse(null);
        return profesor;
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        profesorDao.deleteById(id);
    }

    @Override
    @Transactional
    public void guardar(Profesor profesor) {
        profesorDao.save(profesor);
    }
}
