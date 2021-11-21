// Paquete
package co.edu.udec.proyecto.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entidad autor
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "administradores", schema = "public")
public class Administrador extends Usuario {
 
    // Variables

    /**
     * Hash del administrador
     */
    @Id
    @Column(name = "id")
    private String hash;

    /**
     * Constructor
     */
    public Administrador() {

    }

    // Métodos get & set

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
}
