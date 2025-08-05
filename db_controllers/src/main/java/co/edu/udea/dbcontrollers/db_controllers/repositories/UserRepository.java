package co.edu.udea.dbcontrollers.db_controllers.repositories;

import co.edu.udea.dbcontrollers.db_controllers.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;

/**
 * Repositorio para la entidad User.
 * Proporciona métodos para interactuar con la tabla 'users' en la base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA generará automáticamente la consulta para buscar un usuario por su email.
    // Devuelve un Optional porque el usuario podría no existir.
    Optional<User> findByUserEmail(String email);

    // Spring Data JPA también puede crear una consulta para buscar por 'userName'.
    Optional<User> findByUserName(String userName);

}
