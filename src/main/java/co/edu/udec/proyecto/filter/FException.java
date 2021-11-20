// Paquete
package co.edu.udec.proyecto.filter;

// Librerías
import org.springframework.http.HttpStatus;
import co.edu.udec.proyecto.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import javax.persistence.EntityNotFoundException;
import co.edu.udec.proyecto.exception.ConflictException;
import co.edu.udec.proyecto.exception.NotFoundException;
import co.edu.udec.proyecto.exception.NoContentException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Filtro de exception
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@RestController
@ControllerAdvice
public class FException extends ResponseEntityExceptionHandler {

    // Métodos

    /**
     * Filtro de NO CONTENT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 204 - NO CONTENT
     */
    @ExceptionHandler(NoContentException.class)
    public final ResponseEntity<ExceptionDTO> onNoContentException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.NO_CONTENT, ex, request);
    }

    /**
     * Filtro de NOT FOUND EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 404 - NOT FOUND
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionDTO> onNotFoundException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.NOT_FOUND, ex, request);
    }

    /**
     * Filtro de ENTITY NOT FOUND EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la peitición
     * @return 404 - NOT FOUND
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ExceptionDTO> onentityNotFoundException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.NOT_FOUND, ex, request);
    }

    /**
     * Filtro de CONFLICT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 409 - INTERNAL SERVER ERROR
     */
    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<ExceptionDTO> onConflictException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.CONFLICT, ex, request);
    }

    /**
     * Filtro de EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 500 - INTERNAL SERVER ERROR
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDTO> onException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    /**
     * Retornar objeto de error
     * @param httpStatus Código de error
     * @param ex Excepción generada
     * @param request Datos de la petición
     * @return Objeto de error cargado
     */
    private ResponseEntity<ExceptionDTO> responseError(HttpStatus httpStatus, Exception ex, WebRequest request) {

        // Generando objeto de error
        ExceptionDTO exceptionDTO = new ExceptionDTO(
            httpStatus.value(),
            httpStatus.toString(),
            ex.getMessage(),
            request.getDescription(false)
        );

        // Retornando respuesta cargada
        return new ResponseEntity<ExceptionDTO>(exceptionDTO, httpStatus);
    }
    
}
