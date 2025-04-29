package ve312.com.materiasCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ve312.com.materiasCrud.domain.Profesor;

public interface IprofesorDao extends JpaRepository<Profesor, Long> {
}
