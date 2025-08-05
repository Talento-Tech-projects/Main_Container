package co.edu.udea.dbcontrollers.db_controllers.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class DistributedLoadDTO {

    private Long id;

    @NotNull(message = "La magnitud inicial es obligatoria")
    private Double startMagnitude;

    @NotNull(message = "La magnitud final es obligatoria")
    private Double endMagnitude;

    @NotNull(message = "La posición inicial es obligatoria")
    @PositiveOrZero(message = "La posición inicial debe ser un valor positivo o cero")
    private Double startPosition;

    @NotNull(message = "La posición final es obligatoria")
    @PositiveOrZero(message = "La posición final debe ser un valor positivo o cero")
    private Double endPosition;
    // Nota: La validación de que endPosition >= startPosition se haría en la capa de servicio
    // o con una anotación de validación a nivel de clase personalizada.
    
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
    
}