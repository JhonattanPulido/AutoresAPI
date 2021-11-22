// Paquete
package co.edu.udec.libraryproject.security;

// Librerías
import java.util.Collection;
import java.util.Collections;
import org.springframework.stereotype.Component;
import co.edu.udec.libraryproject.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Custom user details
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Component
public class CustomUserDetails implements UserDetails {
    
    // Variables

    /**
     * Objeto de usuario
     */
    private Usuario usuario;

    /**
     * Constructor
     */
    public CustomUserDetails() {

    }

    /**
     * Constructor
     * @param usuario Datos del usuario
     */
    public CustomUserDetails(Usuario usuario) {
        super();
        this.usuario = usuario;
    }

    // Métodos

    /**
     * Retornar el rol del usuario
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        
        return Collections.singleton(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
    }

    /**
     * Obtener la clave del usuario
     */
    @Override
    public String getPassword() {        
        return usuario.getClave();
    }

    /**
     * Obtener el e-mail del usuario
     */
    @Override
    public String getUsername() {        
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {        
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {        
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {        
        return true;
    }

    @Override
    public boolean isEnabled() {        
        return true;
    }

}
