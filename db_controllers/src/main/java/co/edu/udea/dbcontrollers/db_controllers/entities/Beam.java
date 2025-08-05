package co.edu.udea.dbcontrollers.db_controllers.entities;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "beam")
public class Beam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "beam_length", nullable = false)
    private Double beamLength;

    @Column(name = "E", nullable = false) // Módulo de Young
    private Double E;

    @Column(name = "I", nullable = false) // Momento de Inercia
    private Double I;

    // --- RELACIONES ---

    // Relación Many-to-One con User.
    // Muchas vigas pueden pertenecer a un usuario.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relaciones One-to-Many con los componentes de la viga (apoyos y cargas).
    // CascadeType.ALL: Las operaciones (guardar, borrar) en una viga se propagan a sus componentes.
    // orphanRemoval = true: Si un componente es removido de la lista de la viga, se borra de la BD.
    @OneToMany(mappedBy = "beam", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Support> supports = new HashSet<>();

    @OneToMany(mappedBy = "beam", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PointLoad> pointLoads = new HashSet<>();

    @OneToMany(mappedBy = "beam", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PointMoment> pointMoments = new HashSet<>();

    @OneToMany(mappedBy = "beam", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<DistributedLoad> distributedLoads = new HashSet<>();


    // --- CONSTRUCTORES, GETTERS Y SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBeamLength() {
        return beamLength;
    }

    public void setBeamLength(Double beamLength) {
        this.beamLength = beamLength;
    }

    public Double getE() {
        return E;
    }

    public void setE(Double e) {
        E = e;
    }

    public Double getI() {
        return I;
    }

    public void setI(Double i) {
        I = i;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Support> getSupports() {
        return supports;
    }

    public void setSupports(Set<Support> supports) {
        this.supports = supports;
    }

    public Set<PointLoad> getPointLoads() {
        return pointLoads;
    }

    public void setPointLoads(Set<PointLoad> pointLoads) {
        this.pointLoads = pointLoads;
    }

    public Set<PointMoment> getPointMoments() {
        return pointMoments;
    }

    public void setPointMoments(Set<PointMoment> pointMoments) {
        this.pointMoments = pointMoments;
    }

    public Set<DistributedLoad> getDistributedLoads() {
        return distributedLoads;
    }

    public void setDistributedLoads(Set<DistributedLoad> distributedLoads) {
        this.distributedLoads = distributedLoads;
    }

    
    



    
}
