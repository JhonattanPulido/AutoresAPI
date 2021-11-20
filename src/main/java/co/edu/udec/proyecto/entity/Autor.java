// Paquete
package co.edu.udec.proyecto.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entidad autor
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "autores", schema = "public")
public class Autor {
 
    // Variables

    /**
     * ID del autor
     */
    @Id
    @Column(name = "orcid")
    @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números")
    private String orcid;

    /**
     * Nombres del autor
     */
    @Column(name = "nombres")
    @NotNull(message = "Los nombres del autor son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los nombres del autor debe contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los nombres del autor deben tener mínimo 2 y máximo 24 letras")
    private String nombres;

    /**
     * Apellidos del autor
     */
    @Column(name = "apellidos")
    @NotNull(message = "Los apellidos del autor son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los apellidos del autor deben contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los apellidos del autor deben tener mínimo 2 y máximo 25 letras")
    private String apellidos;

    /**
     * E-mail del autor
     */
    @Column(name = "email")
    @NotNull(message = "El e-mail del autor es requerido")
    @Email(message = "EL e-mail es incorrecto")
    private String email;

    /**
     * Clave del autor
     */
    @Column(name = "clave")
    @NotNull(message = "La clave autor es requerida")
    @Pattern(regexp = "[a-zA-Z0-9@!#-_]*", message = "La clave debe contener únicamente: a-z, A-Z, 0-9, (@!#-_)")
    @Size(min = 8, max = 20, message = "La clave debe tener mínimo 8 y máximo 20 caracteres")
    private String clave;

    /**
     * Constructor
     */
    public Autor() {

    }

    // Métodos get & set

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }    
    
}
