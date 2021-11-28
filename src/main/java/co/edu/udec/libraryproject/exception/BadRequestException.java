// Paquete
package co.edu.udec.libraryproject.exception;

/**
 * Bad request exception
 * @author Jhonattan Pulido
 * @since 27/11/2021
 * @version 1.0.0
 */
public class BadRequestException extends Exception {
    
    // Variables
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param mensaje
     */
    public BadRequestException(String mensaje) {
        super(mensaje);
    }

}
