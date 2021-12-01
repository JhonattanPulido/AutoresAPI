// Paquete
package co.edu.udec.libraryproject.service;

// Librerías
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import co.edu.udec.libraryproject.entity.Autor;
import co.edu.udec.libraryproject.entity.Libro;
import org.springframework.data.domain.PageRequest;
import co.edu.udec.libraryproject.repository.IDAutor;
import co.edu.udec.libraryproject.repository.IDLibro;
import co.edu.udec.libraryproject.service.interfaz.ISLibro;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.exception.NoContentException;
import co.edu.udec.libraryproject.exception.BadRequestException;

/**
 * Capa de servicios de libros
 * @author Jhonattan Pulido
 * @since 28/11/2021
 * @version 1.0.0
 */
@Service
public class SLibro implements ISLibro {
    
    // Variables

    /**
     * Métodos de la capa de datos de libros
     */
    @Autowired
    private IDLibro datosLibro;

    /**
     * Métodos de la capa de datos de autores
     */
    @Autowired
    private IDAutor datosAutor;
    
    /**
     * Constructor
     */
    public SLibro() {

    }

    // Métodos

    /**
     * Crear libro
     * @param libro Datos del libro
     * @throws ConflictException Ya existe un libro con un nombre
     * @deprecated Este método no es óptimo para crear libros
     */
    @Override
    @Deprecated
    public void crear(Libro libro) throws   ConflictException { }

    /**
     * Crear libro
     * @param orcid ORCID del autor
     * @param libro Datos del libro
     * @throws BadRequestException No se encontró el autor
     * @throws ConflictException Ya existe un libro con un nombre
     */    
    @Override
    public void crear(String orcid, Libro libro) throws BadRequestException,
                                                        ConflictException {
        
        // Verificando la existencia del autor
        Autor autor = datosAutor.findById(orcid).orElseThrow(() -> new BadRequestException("No se econtró un autor con el ORCID: " + orcid));

        // Verificando la existencia del libro
        if (datosLibro.existsByNombre(libro.getNombre()))
            throw new ConflictException("Ya existe un libro con el nombre: " + libro.getNombre());

        // Estableciendo la asociación entre libro y autor
        libro.setAutor(autor);

        // Guardando el libro en BD
        datosLibro.save(libro);
        
    }

    /**
     * Leer datos de libro
     * @param id ID del libro
     * @param all True: Traer toda la información asociada al libro | False: Traer únicamente los datos del libro
     * @return Datos del libro
     * @throws NotFoundException No se encontró el libro
     */
    @Override
    public Libro leer(Short id, Boolean all) throws NotFoundException {
        return datosLibro.findById(id).orElseThrow(() -> new NotFoundException("No se encontró un libro con el ID: " + id));
    }

    /**
     * Leer lista de libros paginada
     * @param inicio Indice de la página
     * @param cantidad Cantidad de libros a mostrar
     * @return Lista de libros paginada
     * @throws NoContentException No hay libros para mostrar
     */
    @Override
    public Page<Libro> leer(Short inicio, Short cantidad) throws    NoContentException {        

        // Obteniendo los libros paginados
        Page<Libro> listaLibros = datosLibro.findAll(PageRequest.of(inicio, cantidad));        

        // Verificando si hay libros para mostrar
        if (listaLibros.getNumberOfElements() == 0)
            throw new NoContentException("No hay libros para mostrar");

        // Retornando la lista de libros paginada
        return listaLibros;
    }

    /**
     * Actualizar datos de libro
     * @param libro Datos del libro
     * @throws NotFoundException No se encontró el libro
     * @throws ConflictException Ya existe un libro con un nombre
     */
    @Override
    public void actualizar(Libro libro) throws  NotFoundException,
                                                ConflictException {            

        // Verificando la existencia del libro
        if (datosLibro.existsById(libro.getId()) == false)
            throw new NotFoundException("No se encontró el libro con el ID: " + libro.getId());
        
        // Verificando el nombre del libro
        if (datosLibro.countNameDiff(libro.getId(), libro.getNombre()) > 0)
            throw new ConflictException("Ya existe un libro con el nombre: " + libro.getNombre());

        // Estableciendo el autor del libro
        libro.setAutor(datosAutor.findByLibroId(libro.getId()).orElseThrow(() -> new NotFoundException("No se encontró el autor asociado al libro")));

        // Se actualizan los datos del libro
        datosLibro.save(libro);
        
    }

    /**
     * Eliminar libro
     * @param id ID del libro
     * @throws NotFoundException No se encontró el libro
     */
    @Override
    public void eliminar(Short id) throws   NotFoundException {
        
        // Verificando la existencia del libro
        if (datosLibro.existsById(id) == false)
            throw new NotFoundException("No se encontró un libro con el ID: " + id);

        // Eliminando el libro de la BD
        datosLibro.deleteById(id);

    }    

}
