// Paquete
package co.edu.udec.libraryproject.service.interfaz;

// Librerías
import co.edu.udec.libraryproject.dto.LoginDTO;

/**
 * Interfaz de la capa de datos de authorization
 * @author Jhonattan Pulido
 * @since 23/11/2021
 * @version 1.0.0
 */
public interface ISAuthorization {
    
    // Métodos

    /**
     * Iniciar sesión de usuario
     * @param datos Datos de inicio de sesión
     * @return Token de seguridad
     */
    public String iniciarSesion(LoginDTO datos);

}
