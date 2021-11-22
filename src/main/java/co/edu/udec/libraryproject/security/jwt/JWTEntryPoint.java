// Paquete
package co.edu.udec.libraryproject.security.jwt;

// Librerías
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * JWT entry point
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Component
public class JWTEntryPoint implements AuthenticationEntryPoint {

    // Métodos
    
    /**
     * Responder mensaje de error
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "401: No estas autorizado para realizar esta acción ( ˘︹˘)");
    }
    
}
