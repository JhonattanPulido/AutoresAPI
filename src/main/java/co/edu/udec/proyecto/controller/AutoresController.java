// Paquete
package co.edu.udec.proyecto.controller;

// Librerías
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de autores
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2021
 */
@Validated
@RestController
@RequestMapping(value = "/autores")
public class AutoresController {
    
    // Variables

    /**
     * Constructor
     */
    public AutoresController() {

    }

    // Métodos

    @GetMapping(value = "/{orcid}/{all}", produces = "application/json")
    public ResponseEntity<String> leer(
        @PathVariable String orcid,
        @PathVariable Boolean all
    ) {
        return new ResponseEntity<String>(orcid + "Jhonattan", HttpStatus.OK);
    }

}
