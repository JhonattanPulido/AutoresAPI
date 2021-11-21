// Paquete
package co.edu.udec.proyecto.exception;

// Librerías
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import co.edu.udec.proyecto.dto.ExceptionDTO;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Authentication exception
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
public class AuthenticationJWTException implements AuthenticationEntryPoint {
    
    /**
     * Constructor
     */
    public AuthenticationJWTException() {

    }

    // Métodos

    /**
     * Filtro de AuthenticationJWTException     
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg) throws  IOException, ServletException {    

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), new ExceptionDTO(
            401,
            "UNAUTHORIZED",
            "No esta autorizado para acceder a este recurso",
            request.getServletPath()
        ));

    }

}
