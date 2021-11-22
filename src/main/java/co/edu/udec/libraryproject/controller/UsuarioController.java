// Paquete
package co.edu.udec.libraryproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// Librerías
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de usuario
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
*/
@Validated
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    
    // Variables

    /**
     * Constructor
     */
    public UsuarioController() {
        
    }

    // Métodos

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> helloProof() {
        return new ResponseEntity<String>("Hola Mundo!", HttpStatus.OK);
    }

}
