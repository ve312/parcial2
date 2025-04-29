package ve312.com.materiasCrud.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ve312.com.materiasCrud.dao.IusuarioDao;
import ve312.com.materiasCrud.domain.Rol;
import ve312.com.materiasCrud.domain.Usuario;

import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IusuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            log.error("Usuario no encontrado: " + username);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        log.info("Usuario encontrado: " + usuario.getUsername());
        log.info("Contraseña en BD (encriptada): " + usuario.getPassword());


        var roles = new ArrayList<GrantedAuthority>();

        for (Rol rol : usuario.getRoles()) {
            log.info("Rol asignado: " + rol.getNombre());
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
