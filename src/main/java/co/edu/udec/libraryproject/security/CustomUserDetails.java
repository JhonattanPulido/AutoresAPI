// Paquete
package co.edu.udec.libraryproject.security;

// Librerías
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
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
     * Datos del usuario
     */
    private Usuario usuario;

    /**
     * Permisos del usuario
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor
     */
    public CustomUserDetails() {

    }    

    /**
     * Constructor
     * @param usuario Datos del usuario
     */
    public CustomUserDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        this.usuario = usuario;
        this.authorities = authorities;
    }

    // Métodos    

    public static CustomUserDetails build(Usuario usuario) {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();     
        authorities.add(new SimpleGrantedAuthority(usuario.getRol()));

        return new CustomUserDetails(usuario, authorities);
    }

    /**
     * Retornar el rol del usuario
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        
        return authorities;
    }

    /**
     * Obtener la clave del usuario
     */
    @Override
    public String getPassword() {        
        return usuario.getClave();
    }

    /**
     * Obtener el nombre del usuario
     */
    @Override
    public String getUsername() {        
        return usuario.getNombre();
    }

    /**
     * Obtener los datos del usuario
     * @return Datos del usuario
     */
    public Usuario getUsuario() {
        return usuario;
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
