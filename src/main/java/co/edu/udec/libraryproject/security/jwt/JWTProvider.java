// Paquete
package co.edu.udec.libraryproject.security.jwt;

// Librerías
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import co.edu.udec.libraryproject.security.CustomUserDetails;

/**
 * Java web tokens provider
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Component
public class JWTProvider {
    
    // Variables

    /**
     * Llave secreta para el token
     */
    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * Tiempo de expiración del token
     */
    @Value("${jwt.expiration-time}")
    private int expirationTime;

    /**
     * Constructor
     */
    public JWTProvider() {

    }

    // Métodos

    /**
     * Generar token de seguridad
     * @return Token de seguridad
     */
    public String crearToken(Authentication authentication) {

        // Obteniendo la información del usuario
        CustomUserDetails usuario = (CustomUserDetails) authentication.getPrincipal();        

        // Retornando el token de seguridad
        return Jwts.builder()
                    .setIssuedAt(new Date())
                    .claim("email", usuario.getUsername())
                    .claim("rol", usuario.getRol())
                    .setExpiration(new Date(new Date().getTime() + expirationTime))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

    }

    /**
     * Validar token
     * @param token Token de seguridad
     * @return True: Token correcto | False: Token incorrecto
     * @throws MalformedJwtException Token mal formado
     * @throws UnsupportedJwtException Token no soportado
     * @throws ExpiredJwtException Token expirado
     * @throws IllegalArgumentException Token vacio
     * @throws SignatureException Fallo en la firma del token
     */
    public void validarToken(String token) throws    MalformedJwtException,
                                                        UnsupportedJwtException,
                                                        ExpiredJwtException,
                                                        IllegalArgumentException,
                                                        SignatureException {

        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }

    /**
     * Obtener el e-mail embebido del usuario en el token
     * @param token Token de seguridad
     * @return E-mail del usuario
     */
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email", String.class);
    }

    /**
     * Obtener el rol embebido del usuario en el token
     * @param token Token de seguridad
     * @return Rol del usuario
     */
    public String getRolFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("rol", String.class);
    }

}
