// Paquete
package co.edu.udec.proyecto.exception;

/**
 * Conflict exception
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
public class ConflictException extends Exception {
    
    // Variables
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param mensaje Mensaje de error
     */
    public ConflictException(String mensaje) {
        super(mensaje);
    }

}
