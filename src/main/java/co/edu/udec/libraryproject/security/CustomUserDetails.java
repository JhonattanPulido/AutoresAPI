// Paquete
package co.edu.udec.libraryproject.security;

// Librerías
import java.util.List;
import java.util.ArrayList;
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
     * E-mail del usuario
     */
    private String email;

    /**
     * Clave del usuario
     */
    private String clave;    

    /**
     * Rol del usuario
     */
    private String rol;

    /**
     * Constructor
     */
    public CustomUserDetails() {

    }

    /**
     * Constructor con parámetros
     * @param nombre Nombre del usuario
     * @param email E-mail del usuario
     * @param clave Clave del usuario
     * @param rol Rol del usuario
     */
    public CustomUserDetails(String email, String clave, String rol) {        
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }

    // Métodos    

    public static CustomUserDetails build(Usuario usuario) {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();     
        authorities.add(new SimpleGrantedAuthority(usuario.getRol()));

        return new CustomUserDetails();
    }

    /**
     * Retornar el rol del usuario
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        
        return Collections.singleton(new SimpleGrantedAuthority(rol));
    }

    /**
     * Obtener la clave del usuario
     */
    @Override
    public String getPassword() {        
        return clave;
    }

    /**
     * Obtener el e-mail del usuario
     */
    @Override
    public String getUsername() {        
        return email;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }    

}
