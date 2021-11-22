// Paquete
package co.edu.udec.libraryproject.security;

// Librerías
import org.springframework.stereotype.Service;
import co.edu.udec.libraryproject.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.udec.libraryproject.repository.interfaz.IDUsuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Variables

    /**
     * Métodos de la capa de datos de usuario
     */
    @Autowired
    private IDUsuario datosUsuario;

    /**
     * Constructor
     */
    public CustomUserDetailsService() {

    }

    // Métodos

    /**
     * Obtener la información del usuario y permisos asociados
     * @return UserDetails Datos del usuario
     * @throws UsernameNotFoundException No se encontró el usuario asociado al e-mail
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws   UsernameNotFoundException {
        
        // Verificando la existencia del usuario
        Usuario usuario = datosUsuario.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con el E-mail: " + email));        

        return new CustomUserDetails(usuario);
    }
    
}
