package co.edu.udea.dbcontrollers.db_controllers.entities;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users") // Usamos "users" para evitar conflictos con palabras reservadas de SQL.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 20, unique = true, nullable = false)
    private String userName;

    @Column(name = "user_password", length = 60, nullable = false) // Longitud 60 es estándar para hashes de bcrypt
    private String userPassword;

    @Column(name = "user_email", length = 50, unique = true, nullable = false)
    private String userEmail;

    @Column(name = "user_number")
    private Integer userNumber;

    // Relación One-to-Many con Beam.
    // Un usuario puede tener muchas vigas.
    // mappedBy="user": Indica que la entidad Beam es la dueña de la relación.
    // cascade=CascadeType.ALL: Si se elimina un usuario, se eliminan sus vigas.
    // fetch=FetchType.LAZY: Las vigas no se cargan de la BD a menos que se pidan explícitamente.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Beam> beams = new HashSet<>();

    
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

    public Set<Beam> getBeams() {
        return beams;
    }

    public void setBeams(Set<Beam> beams) {
        this.beams = beams;
    }

    
    
}
