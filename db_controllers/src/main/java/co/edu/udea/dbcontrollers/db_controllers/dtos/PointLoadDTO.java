package co.edu.udea.dbcontrollers.db_controllers.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PointLoadDTO {

    private Long id;

    @NotNull(message = "La magnitud de la carga es obligatoria")
    private Double magnitude;

    @NotNull(message = "La posición de la carga es obligatoria")
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