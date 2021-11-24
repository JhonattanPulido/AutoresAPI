// Paquete
package co.edu.udec.libraryproject.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entidad usuario
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "users", schema = "security")
public class Usuario {
    
    // Variables

    /**
     * ID del usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Min(value = 1, message = "El ID del usuario debe ser mayor a cero (0)")
    private Short id;
    
    /**
     * E-mail del usuario
     */
    @Column(name = "email", columnDefinition = "TEXT")
    @NotNull(message = "El e-mail del usuario es requerido")
    @Email(message = "El e-mail del usuario es incorrecto")
    private String email;

    /**
     * Clave del usuario
     */
    @Column(name = "password", columnDefinition = "TEXT")
    @NotNull(message = "La clave del usuario es requerida")
    private String clave;

    /**
     * Rol del usuario
     */        
    @Column(name = "role")
    @NotNull(message = "El rol del usuario es requerido")    
    private String rol;

    /**
     * Nombre del usuario
     */
    @Column(name = "name")
    @NotNull(message = "El nombre del usuario es requerido")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "El nombre del usuario únicamente debe contener letras")
    @Size(min = 4, max = 48, message = "El nombre del usuario debe contener mínimo 4 y máximo 48 letras")
    private String nombre;

    /**
     * Constructor
     */
    public Usuario() {

    }

    // Métodos get & set

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
