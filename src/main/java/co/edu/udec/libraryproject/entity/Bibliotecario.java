// Paquete
package co.edu.udec.libraryproject.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entidad bibliotecario
 * @author Jhonattan Pulido
 * @since 24/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "librarians", schema = "users")
public class Bibliotecario {
    
    // Variables

    /**
     * ID del bibliotecario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
    private Short id;

    /**
     * Nombres del biblitecario
     */
    @Column(name = "names")
    @NotNull(message = "Los nombres del bibliotecario son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los nombres del bibliotecario deben tener únicamente letras")
    @Size(min = 2, max = 24, message = "Los nombres del bibliotecario deben tener mínimo 2 y máximo 24 letras")
    private String nombres;

    /**
     * Apellidos del biblitecario
     */
    @Column(name = "last_names")
    @NotNull(message = "Los apellidos del bibliotecario son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los apellidos del bibliotecario deben tener únicamente letras")
    @Size(min = 2, max = 24, message = "Los apellidos del bibliotecario deben tener mínimo 2 y máximo 24 letras")
    private String apellidos;

    /**
     * E-mail del biblitecario
     */
    @Column(name = "email")
    @NotNull(message = "El e-mail del bibliotecario es requerido")
    @Email(message = "El e-mail del bibliotecario es incorrecto")
    private String email;

    /**
     * Constructor
     */
    public Bibliotecario() {

    }

    // Métodos get & set

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

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

}
