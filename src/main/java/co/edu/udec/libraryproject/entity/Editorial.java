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
 * Entidad editorial
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "editorials", schema = "library")
public class Editorial {
    
    // Variables

    /**
     * ID de la editorial
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    /**
     * NIT de la editorial
     */
    @Column(name = "nit")
    @NotNull(message = "El NIT de la editorial es requerido")
    @Pattern(regexp = "[0-9]{6,12}", message = "El NIT de la empresa debe contener mínimo 6 y máximo 12 números")    
    private String nit;

    /**
     * Nombre de la editorial
     */
    @Column(name = "name")
    @NotNull(message = "El nombre de la editorial es requerido")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "El nombre de la editorial debe contener únicamente letras")
    @Size(min = 4, max = 50, message = "El nombre de la editorial debe contener mínimo 4 y máximo 50 letras")
    private String nombre;

    /**
     * E-mail de la editorial
     */
    @Column(name = "email")
    @NotNull(message = "El e-mail de la editorial es requerido")
    @Email(message = "El e-mail de la editorial es incorrecto")
    private String email;

    /**
     * Constructor
     */
    public Editorial() {

    }

    // Métodos get & set

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
