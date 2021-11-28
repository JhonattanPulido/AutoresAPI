// Paquete
package co.edu.udec.libraryproject.service.interfaz;

// Librerías
import java.util.List;
import co.edu.udec.libraryproject.entity.Autor;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.exception.BadRequestException;

/**
 * Interfaz de la capa de servicios de autores
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
public interface ISAutor extends ISCRUD<Autor, String> {
    
    // Métodos

    /**
     * Asociar editoriales a autor
     * @param orcid ORCID del autor
     * @param listaNits Lista de NIT de las editoriales
     * @throws NotFoundException No se encontró el autor
     * @throws BadRequestException No se encontró una editorial
     */
    public void asociarEditoriales(String orcid, List<String> listaNits) throws NotFoundException,
                                                                                BadRequestException;

}
