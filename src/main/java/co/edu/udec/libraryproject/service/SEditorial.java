// Paquete
package co.edu.udec.libraryproject.service;

// Librerías
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import co.edu.udec.libraryproject.entity.Usuario;
import co.edu.udec.libraryproject.entity.Editorial;
import co.edu.udec.libraryproject.repository.IDUsuario;
import co.edu.udec.libraryproject.repository.IDEditorial;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.udec.libraryproject.exception.ConflictException;
import co.edu.udec.libraryproject.exception.NotFoundException;
import co.edu.udec.libraryproject.service.interfaz.ISEditorial;
import co.edu.udec.libraryproject.exception.NoContentException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Capa de servicios de editorial
 * @author Jhonattan Pulido
 * @since 27/11/2021
 * @version 1.0.0
 */
@Service
public class SEditorial implements ISEditorial {
    
    // Variables

    /**
     * Métodos de la capa de datos de editoriales
     */
    @Autowired
    private IDEditorial datosEditorial;

    /**
     * Métodos de la capa de datos de usuarios
     */
    @Autowired
    private IDUsuario datosUsuario;

    /**
     * Encriptador de claves
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor
     */
    public SEditorial() {

    }

    // Métodos

    /**
     * Crear editorial
     * @param editorial Datos de la editorial
     * @throws ConflictException Ya existe una editorial con un nit ó un e-mail
     */
    @Transactional
    @Override
    public void crear(Editorial editorial) throws   ConflictException {
        
        // Verificar la integridad del NIT
        if (datosEditorial.existsByNit(editorial.getNit()))
            throw new ConflictException("Ya existe una editorial con el NIT: " + editorial.getNit());

        // Verificar la integridad del e-mail
        if (datosUsuario.existsByEmail(editorial.getEmail()))
            throw new ConflictException("Ya existe un usuario con el e-mail: " + editorial.getEmail());

        // Codificar la clave de la editorial
        editorial.setClave(passwordEncoder.encode(editorial.getClave()));        
        
        // Guardar la editorial en la BD
        datosEditorial.save(editorial);

        // Guardar el usuario en la BD
        datosUsuario.save(new Usuario(editorial.getEmail(), editorial.getClave(), "EDITORIAL", editorial.getNombre()));
        
    }

    /**
     * Leer datos de una editorial
     * @param id ID de la editorial
     * @param all True: Traer toda la información asociada a la editorial | False: Traer únicamente los datos de la editorial
     * @return Datos de la editorial
     * @throws NotFoundException No se encontró la editorial
     */
    @Override
    public Editorial leer(Short id, Boolean all) throws NotFoundException {        
        // Obteniendo los datos de la editorial
        return datosEditorial.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la editorial con el ID: " + id));        
    }

    /**
     * Leer editoriales paginadas
     * @param inicio Indice de la página
     * @param cantidad Cantidad de editoriales a mostrar
     * @return Lista de editoriales paginada
     * @throws NoContentException No hay editoriales para mostrar
     */
    @Override
    public Page<Editorial> leer(Short inicio, Short cantidad) throws NoContentException {
        
        // Obteniendo la lista de editoriales paginada
        Page<Editorial> listaEditoriales = datosEditorial.findAll(PageRequest.of(inicio, cantidad));

        // Verificando si hay libros para mostrar
        if (listaEditoriales.getNumberOfElements() == 0)
            throw new NoContentException("No hay editoriales para mostrar");

        // Retornando la lista de editoriales paginada
        return listaEditoriales;

    }

    /**
     * Actualizar datos de editorial
     * @param editorial Datos de la editorial
     * @throws NotFoundException No se encontró la editorial
     * @throws ConflictException Ya existe una editorial con un nit o un correo electrónico
     */
    @Override
    public void actualizar(Editorial editorial) throws NotFoundException, ConflictException {
        
        // Verificando la existencia de la editorial
        if (datosEditorial.existsById(editorial.getId()) == false)
            throw new NotFoundException("No se encontró una editorial con el ID: " + editorial.getId());
        
        // Verificando la integridad del NIT
        if (datosEditorial.countNitDiff(editorial.getId(), editorial.getNit()) > 0)
            throw new ConflictException("Ya existe una editorial con el NIT: " + editorial.getNit());

        // Verificando la integridad del e-mail
        if (datosEditorial.countEmailDiff(editorial.getId(), editorial.getEmail()) > 0)
            throw new ConflictException("Ya existe una editorial con el e-mail: " + editorial.getEmail());

        // Actualizando la información de la editorial
        datosEditorial.save(editorial);

    }

    @Override
    public void eliminar(Short id) throws NotFoundException {
        // TODO Auto-generated method stub
        
    }

}
