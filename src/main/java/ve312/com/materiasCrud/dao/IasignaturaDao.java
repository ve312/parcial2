package ve312.com.materiasCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ve312.com.materiasCrud.domain.Asignatura;

public interface IasignaturaDao extends JpaRepository<Asignatura, Long>{
}
