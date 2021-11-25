// Paquete
package co.edu.udec.libraryproject.entity;

// Librerías
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Min;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PastOrPresent;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidad libro
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
@Entity
@Table(name = "books", schema = "library")
public class Libro {
    
    // Variables

    /**
     * ID del libro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short Id;

    /**
     * Nombre del libro
     */
    @Column(name = "name")
    @NotNull(message = "El nombre del libro es requerido")
    @Pattern(regexp = "[a-zA-Z0-9ñÑ ]*", message = "El nombde del libro debe contener únicamente letras o números")
    @Size(min = 2, max = 50, message = "El nombre del libro debe tener mínimo 2 y máximo 50 letras")
    private String nombre;

    /**
     * Descripción del libro
     */
    @Column(name = "description")
    @NotNull(message = "La descripción del libro es requerida")
    @Size(min = 8, max = 250, message = "La descripción del libro debe tener mínimo 8 y máximo 50 letras")
    private String descripcion;

    /**
     * Número de páginas
     */
    @Column(name = "page_number")
    @NotNull(message = "El número de páginas del libro es requerido")
    @Min(value = 1, message = "El número de páginas debe ser mayor a cero (0)")
    private Short numeroPaginas;

    /**
     * Fecha de publicación del libro
     */
    @Column(name = "publication_date")
    @NotNull(message = "La fecha de publicación del libro es requerida")
    @PastOrPresent(message = "La fecha de publicación debe ser pasada o actual")
    private Date fechaPublicacion;

    /**
     * Autor del libro
     */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Autor autor;

    /**
     * Constructor
     */
    public Libro() {

    }

    // Métodos get & set

    public Short getId() {
        return Id;
    }

    public void setId(Short id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Short numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @JsonIgnore
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

}
