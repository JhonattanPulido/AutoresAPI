// Paquete
package co.edu.udec.libraryproject.exception;

/**
 * Unauthorized exception
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
public class UnauthorizedException extends RuntimeException {
    
    // Variables
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param mensaje Mensaje de error
     */
    public UnauthorizedException(String mensaje) {
        super(mensaje);
    }

}
