// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.edu.udec.libraryproject.entity.Editorial;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.service.interfaz.ISEditorial;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Controlador de editoriales
 * @author Jhonattan Pulido
 * @since 27/11/2021
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping("/editoriales")
public class EditorialesController {
    
    // Variables

    /**
     * Métodos de la capa de servicios de editorial
     */
    @Autowired
    private ISEditorial serviciosEditorial;

    /**
     * Constructor
     */
    public EditorialesController() {

    }

    // Métodos

    /**
     * Crear editorial
     * @param editorial Datos de la editorial
     * @return 201 - CREATED
     * @throws ConflictException Ya existe una editorial con un NIT o un usuario con un e-mail
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> crear(@Validated @RequestBody Editorial editorial) throws ConflictException {
        serviciosEditorial.crear(editorial);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    /**
     * Leer datos de una editorial
     * @param id ID de la editorial
     * @param all True: Traer toda la información asociada a la editorial | False: Traer únicamente la información de la editorial
     * @return 200 - OK
     * @throws NotFoundException No se encontró la editorial
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO') or hasAuthority('EDITORIAL')")
    @GetMapping(value = "/{id}/{all}", produces = "application/json")
    public ResponseEntity<Editorial> leer(
        @PathVariable @Min(value = 1, message = "El ID de la editorial debe ser mayor a cero (0)") Short id,
        @PathVariable Boolean all
    ) throws    NotFoundException {
        return new ResponseEntity<Editorial>(serviciosEditorial.leer(id, all), HttpStatus.OK);
    }

}
