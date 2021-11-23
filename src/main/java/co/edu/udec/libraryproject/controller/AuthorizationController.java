// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import org.springframework.http.HttpStatus;
import co.edu.udec.libraryproject.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import co.edu.udec.libraryproject.service.SAuthorization;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authorization controller
 * @author Jhonattan Pulido
 * @since 23/11/2021
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationController {
    
    // Variables

    /**
     * Métodos de la capa de servicios de authorization
     */
    @Autowired
    private SAuthorization serviciosAuthorization;    

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
    public ResponseEntity<String> iniciarSesion(@Validated @RequestBody LoginDTO datos) {
        return new ResponseEntity<String>(serviciosAuthorization.iniciarSesion(datos), HttpStatus.OK);
    }

}
