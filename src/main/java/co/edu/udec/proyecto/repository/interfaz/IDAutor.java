// Paquete
package co.edu.udec.proyecto.repository.interfaz;

// Lbrerías
import co.edu.udec.proyecto.entity.Autor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de la capa de datos de autor
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@Repository
public interface IDAutor extends JpaRepository<Autor, String> {
 
    // Métodos

    /**
     * Verificar si un autor ya usa un e-mail
     * @param email E-mail del autor
     * @return True: El e-mail ya está en uso | False: El e-mail NO está en uso 
     */
    public Boolean existsByEmail(String email);
    
}
