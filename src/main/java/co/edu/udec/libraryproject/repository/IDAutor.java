// Paquete
package co.edu.udec.libraryproject.repository;

// Librerías
import java.util.Optional;
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
     * Leer datos de autor a partir de libro
     * @param libroId ID del libro
     * @return Datos del autor
     */
    @Query(value = "SELECT a FROM Autor a, Libro l WHERE l.id = :libro_id AND l.autor.orcid = a.orcid")
    public Optional<Autor> findByLibroId(@Param("libro_id") Short libroId);

    /**
     * Verificar la existencia de un autor con un e-mail diferente de él mismo
     * @param orcid ORCID del autor
     * @param email E-mail del autor
     * @return True: Ya existe otro autor con un e-mail | False: No existe otro autor con el e-mail
     */
    @Query(value = "SELECT COUNT(a) FROM Autor a WHERE a.orcid <> :orcid AND a.email = :email")
    public Long countEmailDiff(@Param("orcid") String orcid, @Param("email") String email);

}