// Paquetes
package co.edu.udec.libraryproject.service.interfaz;

// Librerías
import org.springframework.data.domain.Page;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.exception.NoContentException;

/**
 * Interfaz de la capa de servicios de CRUD
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 * @param T Clase de la entidad
 * @param ID Clase del ID de la entidad
 */
public interface ISCRUD<T, ID> {
    
    // Métodos

    /**
     * Crear registro
     * @param t Datos del registro
     * @throws ConflictException Un ID o campo único ya existe
     */
    public void crear(T t) throws   ConflictException;

    /**
     * Leer datos de un registro
     * @param id ID del registro
     * @param all True: Traer todos los datos asociados al registro | False: Traer únicamente la información principal del registro
     * @return Datos del registro
     * @throws NotFoundException No se encontró el registro
     */
    public T leer(ID id, Boolean all) throws    NotFoundException;

    /**
     * Leer registros paginados
     * @param inicio Indice de la página de inicio
     * @param cantidad Cantidad de registros que se desean mostrar
     * @return Lista de registros paginada
     * @throws NoContentException No hay registros para mostrar
     */
    public Page<T> leer(Short inicio, Short cantidad) throws    NoContentException;

    /**
     * Actualizar datos de un registro
     * @param t Datos del registro
     * @throws NotFoundException No se encontró el registro
     * @throws ConflictException Un ID o campo único ya existe
     */
    public void actualizar(T t) throws  NotFoundException,
                                        ConflictException;

    /**
     * Eliminar registro
     * @param id ID del registro
     * @throws NotFoundException No se encontró el registro
     */                                        
    public void eliminar(ID id) throws  NotFoundException;

}
