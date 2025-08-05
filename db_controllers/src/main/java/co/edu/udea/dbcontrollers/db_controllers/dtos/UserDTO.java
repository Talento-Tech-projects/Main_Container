package co.edu.udea.dbcontrollers.db_controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para la transferencia de datos de la entidad User.
 * Usado en las peticiones y respuestas de la API.
 */
public class UserDTO {

    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 20, message = "El nombre de usuario no puede exceder los 20 caracteres")
    private String userName;

    // Nota: En una aplicación real, el password de escritura y el de lectura se manejarían en DTOs separados
    // para no exponer nunca el password en una respuesta de la API.
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 30, message = "La contraseña debe tener entre 8 y 30 caracteres")
    private String userPassword;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Size(max = 50, message = "El email no puede exceder los 50 caracteres")
    private String userEmail;

    @NotNull(message = "El número de usuario es obligatorio")
    private Integer userNumber;

    
    // --- CONSTRUCTORES, GETTERS Y SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
    
}
