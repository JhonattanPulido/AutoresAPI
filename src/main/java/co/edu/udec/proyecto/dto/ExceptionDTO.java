// Paquete
package co.edu.udec.proyecto.dto;

// Librerías
import java.time.LocalTime;
import java.io.Serializable;

/**
 * Exception DTO
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2021
 */
public class ExceptionDTO implements Serializable {
    
    /**
     * Fecha de error
     */
    private LocalTime timeStamp;

    /**
     * Código de estado del error
     */
    private int status;

    /**
     * Nombre del estado del error
     */
    private String error;

    /**
     * Mensaje de error
     */
    private String message;

    /**
     * Dirección en la que ocurrió el error
     */
    private String path;

    /**
     * Constructor
     */
    public ExceptionDTO() {

    }

    /**
     * Constructor con parámetros
     * @param status
     * @param error
     * @param message
     * @param path
     */
    public ExceptionDTO(int status, String error, String message, String path) {
        timeStamp = LocalTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Métodos set & get

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }  

}
