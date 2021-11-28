// Paquete
package co.edu.udec.libraryproject.repository;

// Librerías
import co.edu.udec.libraryproject.entity.Autor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de la capa de datos de autores
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
@Repository
public interface IDAutor extends JpaRepository<Autor, String> {    
    
    // Métodos

    /**
     * Verificar la existencia de un autor con un e-mail diferente de él mismo
     * @param orcid ORCID del autor
     * @param email E-mail del autor
     * @return True: Ya existe otro autor con un e-mail | False: No existe otro autor con el e-mail
     */
    @Query(value = "SELECT COUNT(a) FROM Autor a WHERE a.orcid <> :orcid AND a.email = :email")
    public Long countEmailDiff(@Param("orcid") String orcid, String email);

}