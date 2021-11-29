// Paquete
package co.edu.udec.libraryproject.repository;

// Librerías
import co.edu.udec.libraryproject.entity.Libro;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Verificar la existencia de un libro con un nombre diferente del mismo
     * @param id ID del libro
     * @param nombre Nombre del libro
     * @return True: Ya existe otro libro con un nombre | False: No existe otro libro con el nombre
     */
    @Query(value = "SELECT COUNT(l) FROM Libro l WHERE l.id <> :id AND l.nombre = :nombre")
    public Long countNameDiff(@Param("id") Short id, @Param("nombre") String nombre);

}
