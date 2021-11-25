// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import org.springframework.http.HttpStatus;
import co.edu.udec.libraryproject.entity.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Autores controller
 * @author Jhonattan Pulido
 * @since 24/11/2021
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping("/autores")
public class AutoresController {
    
    /**
     * Constructor
     */
    public AutoresController() {
        
    }

    // Métodos

    /**
     * Crear autor
     * @param autor Datos del autor
     * @return 201 - CREATED
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @PostMapping(consumes = "application/json", produces = "application/json")    
    public ResponseEntity<Object> crear(@Validated @RequestBody Autor autor) {
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

}
