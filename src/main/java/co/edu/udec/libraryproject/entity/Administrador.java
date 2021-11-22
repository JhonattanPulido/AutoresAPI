// Paquete
package co.edu.udec.libraryproject.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entidad administrador
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "administrators", schema = "user")
public class Administrador {
    
    // Variables

    /**
     * ID del administrador
     */
    @Id
    @Column(name = "id")
    private Short id;

    /**
     * Nombres del administrador
     */
    @Column(name = "names")
    private String nombres;

    /**
     * Apellidos del administrador
     */
    @Column(name = "last_names")
    private String apellidos;

    /**
     * E-mail del administrador
     */
    @Column(name = "email", columnDefinition = "TEXT")
    private String email;

    /**
     * Parámetros
     */
    public Administrador() {

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
