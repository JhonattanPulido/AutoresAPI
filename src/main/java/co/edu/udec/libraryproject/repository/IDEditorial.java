// Paquete
package co.edu.udec.libraryproject.repository;

// Librerías
import java.util.Optional;
import org.springframework.stereotype.Repository;
import co.edu.udec.libraryproject.entity.Editorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
     * Leer datos de editorial por NIT
     * @param nit NIT de la editorial
     * @return Datos de la editorial
     */
    public Optional<Editorial> findByNit(String nit);

    /**
     * Verificar la existencia de una editorial con un NIT
     * @param nit NIT de la editorial
     * @return True: El NIT ya está en uso | False: El NIT no está en uso
     */
    public Boolean existsByNit(String nit);   

    /**
     * Verificar la existencia de un NIT diferente del mismo
     * @param id ID de la editorial
     * @param nit NIT de la editorial
     * @return Cantidad de editoriales
     */
    @Query(value = "SELECT COUNT(e) FROM Editorial e WHERE e.id <> :id AND e.nit = :nit")
    public Long countNitDiff(@Param("id") Short id, @Param("nit") String nit);
    
    @Query(value = "SELECT COUNT(e) FROM Editorial e WHERE e.id <> :id AND e.email = :email")
    public Long countEmailDiff(@Param("id") Short id, @Param("email") String email);

}
