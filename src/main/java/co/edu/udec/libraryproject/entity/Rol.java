// Paquete
package co.edu.udec.libraryproject.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entidad rol
 * @author Jhonattan Pulido
 * @since 22/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "roles", schema = "user")
public class Rol {
    
    // Variables

    /**
     * ID del rol
     */
    @Id
    @Column(name = "id")
    private Short id;

    /**
     * Nombre del rol
     */
    @Column(name = "name")
    private String nombre;

    /**
     * Constructor
     */
    public Rol() {

    }

    // Métodos get & set

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
