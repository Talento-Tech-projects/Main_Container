package co.edu.udea.dbcontrollers.db_controllers.repositories;

import co.edu.udea.dbcontrollers.db_controllers.entities.Beam;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

/**
 * Repositorio para la entidad Beam.
 * Proporciona métodos para interactuar con la tabla 'beam' en la base de datos.
 */
@Repository
public interface BeamRepository extends JpaRepository<Beam, Long> {

    /**
     * Busca todas las vigas que pertenecen a un usuario específico, usando el ID del usuario.
     * Spring Data JPA entiende la relación y crea la consulta:
     * SELECT b FROM Beam b WHERE b.user.id = :userId
     * @param userId El ID del usuario propietario de las vigas.
     * @return Una lista de entidades Beam.
     */
    List<Beam> findByUserId(Long userId);

}
