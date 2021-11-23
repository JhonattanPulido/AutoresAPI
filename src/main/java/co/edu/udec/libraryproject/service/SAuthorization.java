// Paquete
package co.edu.udec.libraryproject.service;

// Librerías
import co.edu.udec.libraryproject.dto.LoginDTO;
import org.springframework.security.core.Authentication;
import co.edu.udec.libraryproject.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.udec.libraryproject.service.interfaz.ISAuthorization;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Capa de servicios de authorization
 * @author Jhonattan Pulido
 * @since 23/11/2021
 * @version 1.0.0
 */
public class SAuthorization implements ISAuthorization {
    
    // Variables    

    /**
     * Métodos de verificación de usuario
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Métodos de creación y validación de java web tokens
     */
    @Autowired
    private JWTProvider jwtProvider;    

    /**
     * Constructor
     */
    public SAuthorization() {

    }

    // Métodos

    /**
     * Iniciar sesión de usuario
     * @param datos Datos de inicio de sesión
     * @return Token de seguridad
     */
    @Override
    public String iniciarSesion(LoginDTO datos) {
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(datos.getEmail(), datos.getClave()));

        SecurityContextHolder.getContext().setAuthentication(authentication);    

        return jwtProvider.crearToken(authentication);

    }

}
