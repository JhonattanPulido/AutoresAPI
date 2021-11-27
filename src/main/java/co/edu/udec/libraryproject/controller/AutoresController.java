// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import org.springframework.http.HttpStatus;
import javax.validation.constraints.Pattern;
import co.edu.udec.libraryproject.entity.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import co.edu.udec.libraryproject.service.interfaz.ISAutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
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
    
    // Variables

    /**
     * Métodos de la capa de servicios de autor
     */
    @Autowired
    private ISAutor serviciosAutor;

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
     * @throws ConflictException Ya existe un autor con un ORCID o un usuario con un e-mail
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @PostMapping(consumes = "application/json", produces = "application/json")    
    public ResponseEntity<Object> crear(@Validated @RequestBody Autor autor) throws ConflictException {
        serviciosAutor.crear(autor);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    /**
     * Leer datos de un autor
     * @param orcid ORCID del autor
     * @param all True: Traer toda la información asociada al autor | False: Traer únicamente la información del autor
     * @return 200 - OK
     * @throws NotFoundException No se encontró el autor
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO') or hasAuthority('AUTOR')")
    @GetMapping(value = "/{orcid}/{all}", produces = "application/json")
    public ResponseEntity<Autor> leer(
        @PathVariable @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números") String orcid, 
        @PathVariable Boolean all) throws   NotFoundException {
        return new ResponseEntity<Autor>(serviciosAutor.leer(orcid, all), HttpStatus.OK);
    }

}
