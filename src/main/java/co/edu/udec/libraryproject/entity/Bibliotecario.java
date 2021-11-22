// Paquete
package co.edu.udec.libraryproject.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entidad bibliotecario
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/11/2021
*/
@Entity
@Table(name = "librarians", schema = "user")
public class Bibliotecario {
    
    // Variables

    /**
     * ID del bibliotecario
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "El ID del bibliotecario debe ser mayor a cero (0)")
    private Short id;

    /**
     * Nombre del bibliotecario
     */
    @Column(name = "names")
    @NotNull(message = "Los nombres del bibliotecario son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los nombres del bibliotecario pueden contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los nombres del bibliotecario debeb contener mínimo 2 y máximo 24 letras")
    private String nombres;

    /**
     * Apellidos del bibliotecario
     */
    @Column(name = "last_names")
    @NotNull(message = "Los apellidos del bibliotecario son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los apellidos del bibliotecario pueden contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los apellidos del bibliotecario debeb contener mínimo 2 y máximo 24 letras")
    private String apellidos;

    /**
     * E-mail del bibliotecario
     */
    @Column(name = "email", columnDefinition = "TEXT")
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

    public String getnombres() {
        return nombres;
    }

    public void setnombres(String nombres) {
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
