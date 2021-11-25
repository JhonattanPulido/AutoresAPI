// Librerías
package co.edu.udec.libraryproject.exception;

/**
 * Not found exception
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
public class NotFoundException extends Exception {
    
    // Variables
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param mensaje Mensaje de error
     */
    public NotFoundException(String mensaje) {
        super(mensaje);
    }

}
