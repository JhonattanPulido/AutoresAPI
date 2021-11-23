// Paquete
package co.edu.udec.libraryproject.dto;

// Librerías
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Login data transfer object
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
public class LoginDTO implements Serializable {
    
    // Variables

    /**
     * E-mail del usuario
     */
    @NotNull(message = "El e-mail del usuario es requerido")
    @Email(message = "El e-mail del usuario es incorrecto")
    private String email;

    /**
     * Clave del usuario
     */
    @NotNull(message = "La clave del usuario es requerida")    
    private String clave;    

    /**
     * Constructor
     */
    public LoginDTO() {

    }

    // Métodos get & set

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
