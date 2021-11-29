// Paquete
package co.edu.udec.libraryproject.service.interfaz;

// Librerías
import co.edu.udec.libraryproject.entity.Libro;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.BadRequestException;

/**
 * Interfaz de la capa de servicios de libros
 * @author Jhonattan Pulido
 * @since 28/11/2021
 * @version 1.0.0
 */
public interface ISLibro extends ISCRUD<Libro, Short> {
 
    // Métodos

    /**
     * Crear libro
     * @param orcid ORCID del autor
     * @param libro Datos del libro
     * @throws BadRequestException No se encontró el autor
     * @throws ConflictException Ya existe un libro con un nombre
     */
    public void crear(String orcid, Libro libro) throws BadRequestException,
                                                        ConflictException;
    
}
