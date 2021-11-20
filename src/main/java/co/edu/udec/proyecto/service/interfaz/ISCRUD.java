// Paquete
package co.edu.udec.proyecto.service.interfaz;

// Librerías
import co.edu.udec.proyecto.exception.ConflictException;
import co.edu.udec.proyecto.exception.NotFoundException;

/**
 * Interfaz genérica de la capa de servicios de CRUD
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 * @param T Clase de la entidad
 * @param ID Clase del tipo de dato del ID de la entidad
 */
public interface ISCRUD<T, ID> {
    
    // Métodos

    /**
     * Crear entidad
     * @param t Datos de la entidad
     * @throws ConflictException Ya existe una entidad con un ID o un dato único
     */
    public void crear(T t) throws   ConflictException;

    /**
     * Leer datos de una entidad
     * @param id ID de la entidad
     * @param all Bandera para saber que tipo de información retornar
     * @return Datos de la entidad
     * @throws NotFoundException No se encontró la entidad
     */
    public T leer(ID id, Boolean all) throws    NotFoundException;

}
