package co.edu.udea.dbcontrollers.db_controllers.dtos;


import co.edu.udea.dbcontrollers.db_controllers.entities.SupportType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class SupportDTO {

    private Long id;

    @NotNull(message = "El tipo de apoyo es obligatorio")
    private SupportType type;

    @NotNull(message = "La posición del apoyo es obligatoria")
    @PositiveOrZero(message = "La posición debe ser un valor positivo o cero")
    private Double position;
    
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
    
    
}