// Paquete
package co.edu.udec.libraryproject.repository;

// Librerías
import co.edu.udec.libraryproject.entity.Autor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de la capa de datos de autores
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
@Repository
public interface IDAutor extends JpaRepository<Autor, String> {    
    
}