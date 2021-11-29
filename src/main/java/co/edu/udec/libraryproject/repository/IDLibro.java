// Paquete
package co.edu.udec.libraryproject.repository;

// Librerías
import co.edu.udec.libraryproject.entity.Libro;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de la capa de datos de libros
 * @author Jhonattan Pulido
 * @since 28/11/2021
 * @version 1.0.0
 */
@Repository
public interface IDLibro extends JpaRepository<Libro, Short> {
    
    // Métodos

    /**
     * Verificar la existencia de un libro por nombre
     * @param nombre Nombre del libro
     * @return True: Ya existe un libro con el nombre | False: No existe un libro con el nombre
     */
    public Boolean existsByNombre(String nombre);

}
