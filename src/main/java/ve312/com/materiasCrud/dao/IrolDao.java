package ve312.com.materiasCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ve312.com.materiasCrud.domain.Rol;

public interface IrolDao extends JpaRepository<Rol, Long> {
}
