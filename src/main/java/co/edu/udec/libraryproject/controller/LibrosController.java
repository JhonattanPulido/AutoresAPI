// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import javax.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import co.edu.udec.libraryproject.entity.Libro;
import org.springframework.http.ResponseEntity;
import co.edu.udec.libraryproject.service.interfaz.ISLibro;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.udec.libraryproject.exception.ConflictException;
import org.springframework.security.access.prepost.PreAuthorize;
import co.edu.udec.libraryproject.exception.BadRequestException;

/**
 * Controlador de libros
 * @author Jhonattan Pulido
 * @since 27/11/2021
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping("/libros")
public class LibrosController {

    // Variables

    /**
     * Métodos de la capa de servicios de libros
     */
    @Autowired
    private ISLibro serviciosLibro;

    /**
     * Constructor
     */
    public LibrosController() {
        
    }
    
    // Métodos

    /**
     * Crear libro
     * @param orcid ORCID del autor
     * @param libro Datos del libro
     * @return 201 - CREATED
     * @throws BadRequestException No se encontró el autor
     * @throws ConflictException Ya existe un libro con un nombre
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @PostMapping(value = "/{orcid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> crear(
        @PathVariable @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números") String orcid,
        @Validated @RequestBody Libro libro) throws BadRequestException, ConflictException {
        serviciosLibro.crear(orcid, libro);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

}
