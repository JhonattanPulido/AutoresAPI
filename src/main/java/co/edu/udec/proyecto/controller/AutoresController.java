// Paquete
package co.edu.udec.proyecto.controller;

// Librerías
import co.edu.udec.proyecto.entity.Autor;
import org.springframework.http.HttpStatus;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import co.edu.udec.proyecto.service.interfaz.ISAutor;
import co.edu.udec.proyecto.exception.ConflictException;
import co.edu.udec.proyecto.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @throws ConflictException Ya existe un autor con un ORCID ó e-mail
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> crear(@Validated @RequestBody Autor autor) throws ConflictException {
        serviciosAutor.crear(autor);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    /**
     * Leer autor
     * @param orcid ORCID del autor
     * @param all Bandera para saber que datos del autor retornar
     * @return Datos del autor 404 - NOT FOUND
     * @throws NotFoundException No se encontró el autor
     */
    @GetMapping(value = "/{orcid}/{all}", produces = "application/json")
    public ResponseEntity<Autor> leer(
        @PathVariable @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números") String orcid,
        @PathVariable @NotNull(message = "Es requerido saber la información que se desea retornar") Boolean all
    ) throws    NotFoundException {
        return new ResponseEntity<Autor>(serviciosAutor.leer(orcid, all), HttpStatus.OK);
    }

}
