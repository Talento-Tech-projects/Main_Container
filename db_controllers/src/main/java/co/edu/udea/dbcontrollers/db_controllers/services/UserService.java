package co.edu.udea.dbcontrollers.db_controllers.services;

import co.edu.udea.dbcontrollers.db_controllers.dtos.UserDTO;

import java.util.List;
import java.util.Optional;



/**
 * Contrato que define las operaciones de negocio para la gestión de Usuarios.
 * La implementación de esta interfaz contendrá la lógica real.
 */
public interface UserService {

    /**
     * Guarda un nuevo usuario en el sistema.
     * @param userDTO El DTO con la información del usuario a crear.
     * @return El DTO del usuario guardado, incluyendo su ID generado.
     */
    UserDTO save(UserDTO userDTO);

    /**
     * Obtiene una lista de todos los usuarios registrados.
     * @return Una lista de UserDTO.
     */
    List<UserDTO> findAll();

    /**
     * Busca un usuario por su ID.
     * Se usa Optional para manejar de forma segura el caso de que el usuario no exista.
     * @param id El ID del usuario a buscar.
     * @return Un Optional que contiene el UserDTO si se encuentra, o un Optional vacío si no.
     */
    Optional<UserDTO> findById(Long id);

    /**
     * Actualiza la información de un usuario existente.
     * @param id El ID del usuario a actualizar.
     * @param userDTO El DTO con la nueva información.
     * @return El DTO del usuario con los datos actualizados.
     */
    UserDTO update(Long id, UserDTO userDTO);

    /**
     * Elimina un usuario del sistema por su ID.
     * @param id El ID del usuario a eliminar.
     */
    void deleteById(Long id);

}