// Paquete
package co.edu.udec.libraryproject.repository;

// Librerías
import org.springframework.stereotype.Repository;
import co.edu.udec.libraryproject.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de la capa de datos de editoriales
 * @author Jhonattan Pulido
 * @since 27/11/2021
 * @version 1.0.0
 */
@Repository
public interface IDEditorial extends JpaRepository<Editorial, Short> {
    
    // Métodos

    /**
     * Verificar la existencia de una editorial con un NIT
     * @param nit NIT de la editorial
     * @return True: El NIT ya está en uso | False: El NIT no está en uso
     */
    public Boolean existsByNit(String nit);   

}
