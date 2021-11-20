// Paquete
package co.edu.udec.proyecto.service;

// Librerías
import co.edu.udec.proyecto.entity.Autor;
import org.springframework.stereotype.Service;
import co.edu.udec.proyecto.service.interfaz.ISAutor;
import co.edu.udec.proyecto.exception.ConflictException;
import co.edu.udec.proyecto.exception.NotFoundException;
import co.edu.udec.proyecto.repository.interfaz.IDAutor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Capa de servicios de autor
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@Service
public class SAutor implements ISAutor {
    
    // Variables

    /**
     * Métodos de la capa de datos de autor
     */
    @Autowired
    private IDAutor datosAutor;

    /**
     * Constructor
     */
    public SAutor() {

    }

    // Métodos

    /**
     * Crear autor
     * @param autor Datos del autor
     * @throws ConflictException Ya existe un autor con un ID ó e-mail
     */
    @Override
    public void crear(Autor autor) throws   ConflictException {        
        
        // Verificar la integridad del ORCID
        if (datosAutor.existsById(autor.getOrcid()))
            throw new ConflictException("El ORCID: " + autor.getOrcid() + " ya está en uso");

        // Verificar la integridad del e-mail
        if (datosAutor.existsByEmail(autor.getEmail()))
            throw new ConflictException("El e-mail: " + autor.getEmail() + " ya está en uso");

        // Guardar el autor en la BD
        datosAutor.save(autor);

    }

    /**
     * Leer datos de un sautor
     * @param orcid ORCID del autor
     * @param all Bandera para saber que datos del autor retornar
     * @throws NotFoundException No se encontró el autor
     */
    @Override
    public Autor leer(String orcid, Boolean all) throws    NotFoundException {
        
        // Obtener los datos del autor
        Autor autor = datosAutor.findById(orcid).orElseThrow(() -> new NotFoundException("No se encontró el autor con el ORCID: " + orcid));        

        // Retornar los datos del autor
        return autor;
        
    }

}
