// Paquete
package co.edu.udec.libraryproject.filter;

// Librerías
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.edu.udec.libraryproject.security.jwt.JWTProvider;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import co.edu.udec.libraryproject.exception.UnauthorizedException;
import co.edu.udec.libraryproject.security.CustomUserDetailsService;

/**
 * Filtro de java web tokens
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
public class FJWT extends OncePerRequestFilter {

    // Variables

    /**
     * Métodos de creación y verificación de token
     */
    @Autowired
    private JWTProvider jwtProvider;

    /**
     * Métodos de la capa de servicios de user detail
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Constructor
     */
    public FJWT() {

    }

    // Métodos

    /**
     * Validar token
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = getToken(request); // Obteniendo el token de la petición

        jwtProvider.validarToken(token); // Validando el token recibido

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtProvider.getEmailFromToken(token)); // Obteniendo la información del usuario de la BD

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
        
    }

    /**
     * Obtener token del header de la petición
     * @param request Datos de la petición
     * @return Token de seguridad
     * @throws UnauthorizedException No se encontró el token en el header
     */
    private String getToken(HttpServletRequest request) throws  UnauthorizedException {

        // Obteniendo el token del header
        String header = request.getHeader("Authorization");

        // Verificando si el token existe
        if (header == null)
            throw new UnauthorizedException("No se encontró el token");

        return header.replace("Bearer", ""); // Se retorna el token

    }
    
}
