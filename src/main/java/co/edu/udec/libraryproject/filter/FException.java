// Paquete
package co.edu.udec.libraryproject.filter;

// Librerías
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.ResponseEntity;
import co.edu.udec.libraryproject.dto.ExceptionDTO;
import javax.validation.ConstraintViolationException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.RestController;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.exception.NoContentException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import co.edu.udec.libraryproject.exception.BadRequestException;
import co.edu.udec.libraryproject.exception.UnauthorizedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Filtro de excepciones
 * @author Jhonattan Pulido
 * @since 22/11/2021
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
        return responseError(HttpStatus.NO_CONTENT, ex.getMessage(), request);
    }

    /**
     * Filtro de BAD REQUEST EXCEPTION
     * @param ex Datos dela excepción
     * @param request Datos de la petición
     * @return 400 - BAD REQUEST
     */
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionDTO> onBadRequestException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    /**
     * Filtro de CONSTRAINT VIOLATION EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 400 - BAD REQUEST
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionDTO> onConstaintViolationException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    /**
     * Filtro de BADCREDENTIALS EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 400 - BAD REQUEST
     */
    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionDTO> onBadCredentialsException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.BAD_REQUEST, "Credenciales incorrectas", request);
    }

    /**
     * Filtro de UNAUTHORIZED EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 401 - UNAUTHORIZED
     */
    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ExceptionDTO> onUnauthorizedException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    /**
     * Filtro de MALFORMEDJWT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 401 - UNAUTHORIZED
     */
    @ExceptionHandler(MalformedJwtException.class)
    public final ResponseEntity<ExceptionDTO> onMalformedJwtException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    /**
     * Filtro de UNSUPPORTEDJWT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 401 - UNAUTHORIZED
     */
    @ExceptionHandler(UnsupportedJwtException.class)
    public final ResponseEntity<ExceptionDTO> onUnsupportedJwtException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    /**
     * Filtro de ILLEGALARGUMENT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 401 - UNAUTHORIZED
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionDTO> onIllegalArgumentException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    /**
     * Filtro de SIGNATURE EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 401 - UNAUTHORIZED
     */
    @ExceptionHandler(SignatureException.class)
    public final ResponseEntity<ExceptionDTO> onSignatureException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    /**
     * Filtro de EXPIREDJWT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 401 - UNAUTHORIZED
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<ExceptionDTO> onExpiredJwtException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    /**
     * Filtro de NOT FOUND EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 404 - NOT FOUND
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionDTO> onNotFoundException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    /**
     * Filtro de CONFLICT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 409 - CONFLICT
     */
    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<ExceptionDTO> onConflictException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    /**
     * Filtro de EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 500 - INTERNAL SERVER ERROR
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDTO> onException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }    

    /**
     * Retornar objeto de error
     * @param httpStatus Código de error
     * @param mensaje Mensaje de la excepción
     * @param request Datos de la petición
     * @return Objeto de error cargado
     */
    private ResponseEntity<ExceptionDTO> responseError(HttpStatus httpStatus, String mensaje, WebRequest request) {

        // Generando objeto de error
        ExceptionDTO exceptionDTO = new ExceptionDTO(
            httpStatus.value(),
            httpStatus.toString(),
            mensaje,
            request.getDescription(false)
        );

        // Retornando respuesta cargada
        return new ResponseEntity<ExceptionDTO>(exceptionDTO, httpStatus);

    }
    
}
