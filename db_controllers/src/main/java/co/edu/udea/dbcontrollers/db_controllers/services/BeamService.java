package co.edu.udea.dbcontrollers.db_controllers.services;

import co.edu.udea.dbcontrollers.db_controllers.dtos.BeamDTO;

import java.util.List;
import java.util.Optional;



/**
 * Contrato que define las operaciones de negocio para la gestión de Vigas (Beams).
 * La implementación de esta interfaz contendrá la lógica de negocio.
 */
public interface BeamService {

    /**
     * Guarda una nueva viga y todos sus componentes (apoyos, cargas, etc.).
     * @param beamDTO El DTO que representa la viga completa a crear.
     * @return El DTO de la viga guardada, con los IDs generados para ella y sus componentes.
     */
    BeamDTO save(BeamDTO beamDTO);

    /**
     * Obtiene una lista de todas las vigas en el sistema.
     * @return Una lista de todos los BeamDTO.
     */
    List<BeamDTO> findAll();

    /**
     * Busca una viga específica por su ID.
     * @param id El ID de la viga a buscar.
     * @return Un Optional que contiene el BeamDTO si se encuentra, o un Optional vacío si no.
     */
    Optional<BeamDTO> findById(Long id);

    /**
     * Busca todas las vigas que pertenecen a un usuario específico.
     * @param userId El ID del usuario propietario.
     * @return Una lista de BeamDTO pertenecientes a ese usuario. Puede ser una lista vacía.
     */
    List<BeamDTO> findByUserId(Long userId);

    /**
     * Actualiza la información de una viga existente.
     * Esto puede implicar añadir, eliminar o modificar sus componentes.
     * @param id El ID de la viga a actualizar.
     * @param beamDTO El DTO con la nueva información de la viga y sus componentes.
     * @return El DTO de la viga con los datos ya actualizados.
     */
    BeamDTO update(Long id, BeamDTO beamDTO);

    /**
     * Elimina una viga del sistema por su ID.
     * Gracias a la configuración de cascada, esto también eliminará todos sus componentes asociados.
     * @param id El ID de la viga a eliminar.
     */
    void deleteById(Long id);

}