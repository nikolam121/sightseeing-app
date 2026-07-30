package hr.tis.academy.sightseeingapp.dto;

import hr.tis.academy.sightseeingapp.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewAttractionFormDto {

    @NotBlank
    @Size(max = 30)
    private String place;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 250)
    private String description;

    @NotNull
    private Type type;

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
}