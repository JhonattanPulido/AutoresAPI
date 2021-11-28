// Paquete
package co.edu.udec.libraryproject.controller;

// Librerías
import java.util.List;
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import javax.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import co.edu.udec.libraryproject.entity.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import co.edu.udec.libraryproject.service.interfaz.ISAutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.validation.annotation.Validated;
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

    /**
     * Leer datos de autores paginados
     * @param inicio Indice de la página de inicio
     * @param cantidad Cantidad de autores que se desean ver
     * @return 200 - OK
     * @throws NoContentException No hay autores para mostrar
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @GetMapping(value = "/pag/{inicio}/{cantidad}", produces = "application/json")
    public ResponseEntity<Page<Autor>> leer(
        @PathVariable @Min(value = 0, message = "El índice de la página de inicio debe ser mayor o igual a cero (0)") Short inicio,
        @PathVariable @Min(value = 1, message = "La cantidad de autores a mostrar debe ser mayor a cero (0)") Short cantidad
    ) throws    NoContentException {
        return new ResponseEntity<Page<Autor>>(serviciosAutor.leer(inicio, cantidad), HttpStatus.OK);
    }

    /**
     * Actualizar datos de autor
     * @param autor Datos del autor
     * @return 200 - OK
     * @throws NotFoundException No se encontró el autor
     * @throws ConflictException Ya existe un autor con un e-mail
     */
    @PreAuthorize("hasAuthority('AUTOR')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> actualizar(@Validated @RequestBody Autor autor) throws    NotFoundException,
                                                                                            ConflictException {
        serviciosAutor.actualizar(autor);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * Eliminar autor
     * @param orcid ORCID del autor
     * @return 204 - NO CONTENT
     * @throws NotFoundException No se encontró el autor
     */
    @PreAuthorize("hasAuthority('BIBLIOTECARIO')")
    @DeleteMapping(value = "/{orcid}", produces = "application/json")
    public ResponseEntity<Object> eliminar(
        @PathVariable @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números") String orcid
    ) throws    NotFoundException {
        serviciosAutor.eliminar(orcid);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /**
     * Asociar editoriales a autor
     * @param orcid ORCID del autor
     * @param listaNits Lista de NIT de las editoriales
     * @return 200 - OK
     * @throws NotFoundException No se encontró el autor
     * @throws BadRequestException No se encontró una editorial
     */
    @PreAuthorize("hasAuthority('AUTOR')")
    @PostMapping(value = "asociar-editoriales/{orcid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> asociarEditoriales(
        @PathVariable @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números") String orcid,
        @RequestBody List<String> listaNits) throws NotFoundException,
                                                    BadRequestException {        
        serviciosAutor.asociarEditoriales(orcid, listaNits);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }    

}
