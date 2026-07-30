package hr.tis.academy.sightseeingapp.model;


import hr.tis.academy.sightseeingapp.enums.Type;
import jakarta.persistence.*;

import java.text.Normalizer;

@Entity
@Table(name ="ATTRACTION", schema = "SIGHTSEEING")
public class Attraction {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long Id;

    @Column
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Attraction() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getUrlName() {
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        normalized = normalized.replace("đ", "d");
        normalized = normalized.replace("Đ", "D");
        return normalized.replaceAll("\\s", "%20");
    }
}