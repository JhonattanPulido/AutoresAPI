// Paquete
package co.edu.udec.libraryproject.entity;

// Librerías
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Transient;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entidad autor
 * @author Jhonattan Pulido
 * @since 24/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "authors", schema = "users")
public class Autor {

    // Variables

    /**
     * ORCID del autor
     */
    @Id    
    @Column(name = "orcid")    
    @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números")
    private String orcid;

    /**
     * Nombres del autor
     */
    @Column(name = "names")
    @NotNull(message = "Los nombres del autor son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los nombres del autor deben contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los nombres del autor deben tener mínimo 2 y máximo 24 letras")
    private String nombres;

    /**
     * Apellidos del autor
     */
    @Column(name = "last_names")
    @NotNull(message = "Los apellidos del autor son requeridos")
    @Pattern(regexp = "[a-zA-ZñÑ ]*", message = "Los apellidos del autor deben contener únicamente letras")
    @Size(min = 2, max = 24, message = "Los apellidos del autor deben tener mínimo 2 y máximo 24 letras")
    private String apellidos;

    /**
     * E-mail del autor
     */
    @Column(name = "email", columnDefinition = "TEXT")
    @NotNull(message = "El e-mail del autor es requerido")
    @Email(message = "El e-mail del autor es incorrecto")
    private String email;

    /**
     * Clave del autor
     */
    @Transient        
    private String clave;

    /**
     * Lista de libros asociados al autor
     */
    @OneToMany(mappedBy = "autor", cascade = { CascadeType.ALL }, orphanRemoval = false)
    private List<Libro> listaLibros;

    /**
     * Lista de editoriales asociadas al autor
     */
    @ManyToMany
    @JoinTable(name = "authors_editorials", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "editorial_id"))
    private List<Editorial> listaEditoriales;

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

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public List<Editorial> getListaEditoriales() {
        return listaEditoriales;
    }

    public void setListaEditoriales(List<Editorial> listaEditoriales) {
        this.listaEditoriales = listaEditoriales;
    }

}