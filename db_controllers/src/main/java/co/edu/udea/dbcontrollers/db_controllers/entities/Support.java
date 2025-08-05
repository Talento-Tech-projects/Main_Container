package co.edu.udea.dbcontrollers.db_controllers.entities;



import jakarta.persistence.*;

@Entity
@Table(name = "support")
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usa el Enum SupportType, almacenado como String en la BD.
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 10, nullable = false)
    private SupportType type;

    @Column(name = "position", nullable = false)
    private Double position;

    // Relación Many-to-One con Beam.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beam_id", nullable = false)
    private Beam beam;
    
    // --- CONSTRUCTORES, GETTERS Y SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SupportType getType() {
        return type;
    }

    public void setType(SupportType type) {
        this.type = type;
    }

    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }

    public Beam getBeam() {
        return beam;
    }

    public void setBeam(Beam beam) {
        this.beam = beam;
    }
    
    
}
