// Paquete
package co.edu.udec.proyecto.entity;

// Librerías
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

/**
 * Entidad autor
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "autores", schema = "public")
public class Autor extends Usuario {
 
    // Variables

    /**
     * ID del autor
     */
    @Id
    @Column(name = "orcid")
    @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números")
    private String orcid;    

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
    
}
