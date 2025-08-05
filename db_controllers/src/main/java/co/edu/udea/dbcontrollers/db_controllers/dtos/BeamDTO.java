package co.edu.udea.dbcontrollers.db_controllers.dtos;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

/**
 * DTO principal para la entidad Beam.
 * Agrupa la información de la viga y sus componentes (apoyos, cargas, etc.).
 */
public class BeamDTO {

    private Long id;

    @NotNull(message = "La longitud de la viga es obligatoria")
    @Positive(message = "La longitud debe ser un valor positivo")
    private Double beamLength;

    @NotNull(message = "El módulo de Young (E) es obligatorio")
    @Positive(message = "El módulo de Young (E) debe ser un valor positivo")
    private Double E;

    @NotNull(message = "El momento de inercia (I) es obligatorio")
    @Positive(message = "El momento de inercia (I) debe ser un valor positivo")
    private Double I;

    // En las peticiones, este campo será obligatorio para asociar la viga a un usuario.
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    // Las listas de componentes.
    // La anotación @Valid aquí es un recordatorio de que en el Controller se debe usar
    // para que se validen los objetos dentro de estas listas.
    @Valid 
    private List<SupportDTO> supports;
    
    @Valid
    private List<PointLoadDTO> pointLoads;
    
    @Valid
    private List<PointMomentDTO> pointMoments;
    
    @Valid
    private List<DistributedLoadDTO> distributedLoads;
    
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SupportDTO> getSupports() {
        return supports;
    }

    public void setSupports(List<SupportDTO> supports) {
        this.supports = supports;
    }

    public List<PointLoadDTO> getPointLoads() {
        return pointLoads;
    }

    public void setPointLoads(List<PointLoadDTO> pointLoads) {
        this.pointLoads = pointLoads;
    }

    public List<PointMomentDTO> getPointMoments() {
        return pointMoments;
    }

    public void setPointMoments(List<PointMomentDTO> pointMoments) {
        this.pointMoments = pointMoments;
    }

    public List<DistributedLoadDTO> getDistributedLoads() {
        return distributedLoads;
    }

    public void setDistributedLoads(List<DistributedLoadDTO> distributedLoads) {
        this.distributedLoads = distributedLoads;
    }
    
}
