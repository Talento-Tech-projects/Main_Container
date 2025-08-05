package co.edu.udea.dbcontrollers.db_controllers.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "distributed_load")
public class DistributedLoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_magnitude", nullable = false)
    private Double startMagnitude;

    @Column(name = "end_magnitude", nullable = false)
    private Double endMagnitude;

    @Column(name = "start_position", nullable = false)
    private Double startPosition;

    @Column(name = "end_position", nullable = false)
    private Double endPosition;

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

    public Double getStartMagnitude() {
        return startMagnitude;
    }

    public void setStartMagnitude(Double startMagnitude) {
        this.startMagnitude = startMagnitude;
    }

    public Double getEndMagnitude() {
        return endMagnitude;
    }

    public void setEndMagnitude(Double endMagnitude) {
        this.endMagnitude = endMagnitude;
    }

    public Double getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Double startPosition) {
        this.startPosition = startPosition;
    }

    public Double getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Double endPosition) {
        this.endPosition = endPosition;
    }

    public Beam getBeam() {
        return beam;
    }

    public void setBeam(Beam beam) {
        this.beam = beam;
    }
    

    
}