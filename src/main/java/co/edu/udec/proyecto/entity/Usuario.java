// Paquete
package co.edu.udec.proyecto.entity;

// Librerías
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entidad usuario
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2021
 */
public class Usuario {
   
    // Variables

    /**
     * Nombres del usuario
     */
    @Column(name = "nombres")
    @NotNull(message = "Los nombres del usuario son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los nombres del usuario debe contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los nombres del usuario deben tener mínimo 2 y máximo 24 letras")
    private String nombres;

    /**
     * Apellidos del usuario
     */
    @Column(name = "apellidos")
    @NotNull(message = "Los apellidos del usuario son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los apellidos del usuario deben contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los apellidos del usuario deben tener mínimo 2 y máximo 25 letras")
    private String apellidos;

    /**
     * E-mail del usuario
     */
    @Column(name = "email")
    @NotNull(message = "El e-mail del usuario es requerido")
    @Email(message = "EL e-mail es incorrecto")
    private String email;

    /**
     * Clave del usuario
     */
    @Column(name = "clave")
    @NotNull(message = "La clave del usuario es requerida")
    @Pattern(regexp = "[a-zA-Z0-9@!#-_]*", message = "La clave debe contener únicamente: a-z, A-Z, 0-9, (@!#-_)")
    @Size(min = 8, max = 20, message = "La clave debe tener mínimo 8 y máximo 20 caracteres")
    private String clave;

    /**
     * Constructor
     */
    public Usuario() {

    }

    // Métodos get & set

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

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
