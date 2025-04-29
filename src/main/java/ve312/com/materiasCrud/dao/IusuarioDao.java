package ve312.com.materiasCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ve312.com.materiasCrud.domain.Usuario;

public interface IusuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
