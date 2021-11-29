// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import javax.validation.constraints.Pattern;
import co.edu.udec.libraryproject.entity.Libro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import co.edu.udec.libraryproject.service.interfaz.ISLibro;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.exception.NoContentException;
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

    /**
     * Leer datos de libro
     * @param id ID del libro
     * @return 200 - OK
     * @throws NotFoundException No se encontró el libro
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO') or hasAuthority('AUTOR')")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Libro> leer(
        @PathVariable @Min(value = 1, message = "El ID del libro debe ser mayor a cero (0)") Short id
    ) throws NotFoundException {
        return new ResponseEntity<>(serviciosLibro.leer(id, true), HttpStatus.OK);
    }

    /**
     * Leer libros paginados
     * @param inicio Indice de la página de inicio
     * @param cantidad Cantidad de libros a mostrar
     * @return 200 - OK
     * @throws NoContentException No hay libros para mostrar
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @GetMapping(value = "/pag/{inicio}/{cantidad}", produces = "application/json")
    public ResponseEntity<Page<Libro>> leer(
        @PathVariable @Min(value = 0, message = "El índice de la página debe ser mayor o igual a cero (0)") Short inicio,
        @PathVariable @Min(value = 1, message = "La cantidad de libros a mostrar debe ser mayor a cero (0)") Short cantidad
    ) throws    NoContentException {
        return new ResponseEntity<Page<Libro>>(serviciosLibro.leer(inicio, cantidad), HttpStatus.OK);
    }

    /**
     * Actualizar datos de libro
     * @param libro Datos del libro
     * @return 200 - OK
     * @throws NotFoundException No se encontró el libro
     * @throws ConflictException Ya existe un libro con un nombre
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> actualizar(@Validated @RequestBody Libro libro) throws    NotFoundException,
                                                                                            ConflictException {
        serviciosLibro.actualizar(libro);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * Eliminar libro
     * @param id ID del libro
     * @return 204 - NO CONTENT
     * @throws NotFoundException No se encontró el libro
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> eliminar(@PathVariable @Min(value = 1, message = "El ID del libro debe ser mayor a cero (0)") Short id) throws    NotFoundException {
        serviciosLibro.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
