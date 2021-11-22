// Paquete
package co.edu.udec.libraryproject.entity;

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
 * @since 22/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "authors", schema = "user")
public class Autor {
    
    // Variables

    /**
     * ORCID del autor
     */
    @Id
    @Column(name = "orcid")
    @NotNull(message = "El ORCID del autor es requerido")
    @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números")
    private String orcid;

    /**
     * Nombres del autor
     */
    @Column(name = "names")
    @NotNull(message = "Los nombres del autor son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los nombres del autor deben contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los nombres del autor deben contener mínimo 2 y máximo 24 letras")
    private String nombres;

    /**
     * Apellidos del autor
     */
    @Column(name = "last_names")
    @NotNull(message = "Los apellidos del autor son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los apellidos del autor deben contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los apellidos del autor deben contener mínimo 2 y máximo 24 letras")
    private String apellidos;

    /**
     * E-mail del autor
     */
    @Column(name = "email", columnDefinition = "TEXT")
    @NotNull(message = "El e-mail del autor es requerido")
    @Email(message = "El e-mail del autor es incorrecto")
    private String email;

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
    
}