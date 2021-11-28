// Paquete
package co.edu.udec.libraryproject.service;

// Librerías
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import co.edu.udec.libraryproject.entity.Autor;
import co.edu.udec.libraryproject.entity.Libro;
import co.edu.udec.libraryproject.entity.Usuario;
import co.edu.udec.libraryproject.entity.Editorial;
import co.edu.udec.libraryproject.repository.IDAutor;
import co.edu.udec.libraryproject.repository.IDUsuario;
import co.edu.udec.libraryproject.repository.IDEditorial;
import co.edu.udec.libraryproject.service.interfaz.ISAutor;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.exception.NoContentException;
import org.springframework.transaction.annotation.Transactional;
import co.edu.udec.libraryproject.exception.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Capa de servicios de autores
 * @author Jhonattan Pulido
 * @since 25/11/2021
 * @version 1.0.0
 */
@Service
public class SAutor implements ISAutor {
    
    // Variables

    /**
     * Métodos de la capa de datos de autores
     */
    @Autowired
    private IDAutor datosAutor;

    /**
     * Métodos de la capa de datos de usuarios
     */
    @Autowired
    private IDUsuario datosUsuario;

    /**
     * Métodos de la capa de datos de editoriales
     */
    @Autowired
    private IDEditorial datosEditorial;    

    /**
     * Encriptador de claves
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor
     */
    public SAutor() {

    }

    // Métodos

    /**
     * Crear autor
     * @param autor Datos del autor
     * @throws ConflictException Ya existe un autor con un ORCID o un usuario con un e-mail
     */
    @Transactional
    @Override
    public void crear(Autor autor) throws   ConflictException {
        
        // Validar si el ORCID ya está en uso
        if (datosAutor.existsById(autor.getOrcid()))
            throw new ConflictException("El ORCID: " + autor.getOrcid() + " ya está en uso");

        // Validar si el e-mail ya está en uso
        if (datosUsuario.existsByEmail(autor.getEmail()))
            throw new ConflictException("El e-mail: " + autor.getEmail() + " ya está en uso");

        // Codificar la clave del autor
        autor.setClave(passwordEncoder.encode(autor.getClave()));

        // Guardar los libros asociados al autor
        for (Libro libro: autor.getListaLibros())
            libro.setAutor(autor);

        // Guardar el autor en la BD
        datosAutor.save(autor);

        // Guardar el usuario en la BD
        datosUsuario.save(new Usuario(autor.getEmail(), autor.getClave(), "AUTOR", autor.getNombres() + " " + autor.getApellidos()));
        
    }

    /**
     * Leer datos de un autor
     * @param orcid ORCID del autor
     * @param all True: Retornar toda la información relacionada al autor | False: Traer únicamente la información del autor
     * @return Datos del autor
     * @throws NotFoundException No se encontró el autor
     */
    @Override
    public Autor leer(String orcid, Boolean all) throws NotFoundException {
        
        // Leer los datos del autor
        Autor autor = datosAutor.findById(orcid).orElseThrow(() -> new NotFoundException("No se encontró un autor con el ORCID: " + orcid));

        // Evaluar la información que se debe retornar
        if (all == false) {
            autor.setListaLibros(null);
            autor.setListaEditoriales(null);
        }

        // Retornar la información del autor
        return autor;

    }

    @Override
    public Page<Autor> leer(Short inicio, Short cantidad) throws NoContentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(Autor t) throws NotFoundException, ConflictException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void eliminar(String id) throws NotFoundException {
        // TODO Auto-generated method stub
        
    }

    /**
     * Asociar editoriales a autor
     * @param orcid ORCID del autor
     * @param listaNits Lista de NIT de las editoriales
     * @throws NotFoundException No se encontró el autor
     * @throws BadRequestException No se encontró una editorial
     */
    @Override
    public void asociarEditoriales(String orcid, List<String> listaNits) throws NotFoundException,
                                                                                BadRequestException {
        
        // Verificando la existencia del autor
        Autor autor = datosAutor.findById(orcid).orElseThrow(() -> new NotFoundException("No se encontró el autor con el ORCID: " + orcid));
        
        // Declarando la lista de editoriales
        List<Editorial> listaEditoriales = new ArrayList<>();

        // Recorriendo la lista de nits de editoriales
        for (String nit : listaNits) {
            Editorial editorial = datosEditorial.findByNit(nit).orElseThrow(() -> new BadRequestException("No se encontró la editorial con el NIT: " + nit));
            listaEditoriales.add(editorial);
        }            
            
        // Asociando las editoriales a el autor
        autor.setListaEditoriales(listaEditoriales);

        // Actualizar la información del autor
        datosAutor.save(autor);
        
    }

}
