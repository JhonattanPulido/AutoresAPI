// Paquete
package co.edu.udec.libraryproject.exception;

/**
 * No content exception
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
public class NoContentException extends Exception {
    
    // Variables
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param mensaje Mensaje de error
     */
    public NoContentException(String mensaje) {
        super(mensaje);
    }

}
