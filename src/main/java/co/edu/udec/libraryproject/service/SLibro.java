// Paquete
package co.edu.udec.libraryproject.service;

// Librerías
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import co.edu.udec.libraryproject.entity.Autor;
import co.edu.udec.libraryproject.entity.Libro;
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

    @Override
    public Libro leer(Short id, Boolean all) throws NotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Libro> leer(Short inicio, Short cantidad) throws NoContentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(Libro t) throws NotFoundException, ConflictException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void eliminar(Short id) throws NotFoundException {
        // TODO Auto-generated method stub
        
    }    

}
