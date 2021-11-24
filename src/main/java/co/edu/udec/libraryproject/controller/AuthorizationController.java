// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import org.springframework.http.HttpStatus;
import co.edu.udec.libraryproject.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import co.edu.udec.libraryproject.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Authorization controller
 * @author Jhonattan Pulido
 * @since 23/11/2021
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    
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
    public AuthorizationController() {

    }

    // Métodos

    /**
     * Iniciar sesión
     * @param datos Datos de inicio de sesión
     * @return Token de seguridad
     */
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> iniciarSesion(@Validated @RequestBody LoginDTO datos) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(datos.getEmail(), datos.getClave()));

        SecurityContextHolder.getContext().setAuthentication(authentication);    
       
        return new ResponseEntity<String>(jwtProvider.crearToken(authentication), HttpStatus.OK);
    }

}
