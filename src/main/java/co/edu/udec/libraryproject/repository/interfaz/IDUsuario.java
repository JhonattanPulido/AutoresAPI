// Paquete
package co.edu.udec.libraryproject.repository.interfaz;

// Librerías
import org.springframework.stereotype.Repository;
import co.edu.udec.libraryproject.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de la capa de datos de usuario
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Repository
public interface IDUsuario extends JpaRepository<Usuario, Short> {
    
    // Métodos

    /**
     * Leer datos de usuario filtrados por E-mail
     * @param email E-mail del usuario
     * @return Datos del usuario
     */
    public Optional<Usuario> findByEmail(String email);

}
