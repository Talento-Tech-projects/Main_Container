package co.edu.udea.dbcontrollers.db_controllers.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "point_load")
public class PointLoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "magnitude", nullable = false)
    private Double magnitude;

    @Column(name = "position", nullable = false)
    private Double position;

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

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
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
