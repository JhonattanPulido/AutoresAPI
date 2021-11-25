// Paquete
package co.edu.udec.libraryproject.service;

// Librerías
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import co.edu.udec.libraryproject.entity.Autor;
import co.edu.udec.libraryproject.entity.Usuario;
import co.edu.udec.libraryproject.repository.IDAutor;
import co.edu.udec.libraryproject.repository.IDUsuario;
import co.edu.udec.libraryproject.service.interfaz.ISAutor;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.exception.NoContentException;

/**
 * Capa de servicios de autores
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
@Service
public class SAutor implements ISAutor {
    
    // Variables

    /**
     * Métodos de la capa de datos de autores
     */
    @Autowired
    private IDAutor datosAutor;

    /**
     * Métodos de la capa de datos de usuarios
     */
    @Autowired
    private IDUsuario datosUsuario;

    /**
     * Constructor
     */
    public SAutor() {

    }

    // Métodos

    /**
     * Crear autor
     * @param autor Datos del autor
     * @throws ConflictException Ya existe un autor con un ORCID o un usuario con un e-mail
     */
    @Override
    public void crear(Autor autor) throws   ConflictException {
        
        // Validar si el ORCID ya está en uso
        if (datosAutor.existsById(autor.getOrcid()))
            throw new ConflictException("El ORCID: " + autor.getOrcid() + " ya está en uso");

        // Validar si el e-mail ya está en uso
        if (datosUsuario.existsByEmail(autor.getEmail()))
            throw new ConflictException("El e-mail: " + autor.getEmail() + " ya está en uso");

        // Guardar el autor en la BD
        datosAutor.save(autor);

        // Guardar el usuario en la BD
        datosUsuario.save(new Usuario(autor.getEmail(), autor.getClave(), "AUTOR", autor.getNombres() + " " + autor.getApellidos()));
        
    }

    @Override
    public Autor leer(String id, Boolean all) throws NotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Autor> leer(Short inicio, Short cantidad) throws NoContentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(Autor t) throws NotFoundException, ConflictException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void eliminar(String id) throws NotFoundException {
        // TODO Auto-generated method stub
        
    }

}
