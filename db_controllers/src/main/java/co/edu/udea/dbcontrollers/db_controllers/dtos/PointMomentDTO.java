package co.edu.udea.dbcontrollers.db_controllers.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PointMomentDTO {

    private Long id;

    @NotNull(message = "La magnitud del momento es obligatoria")
    private Double magnitude;

    @NotNull(message = "La posición del momento es obligatoria")
    @PositiveOrZero(message = "La posición debe ser un valor positivo o cero")
    private Double position;
    
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
    
}