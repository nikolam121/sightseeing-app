package hr.tis.academy.sightseeingapp.model;


import hr.tis.academy.sightseeingapp.enums.Type;
import jakarta.persistence.*;

@Entity
@Table(name ="ATTRACTION", schema = "SIGHTSEEING")
public class Attraction {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column
    private String attractionName;

    @Column
    private String attractionDescription;


    @Enumerated(EnumType.STRING)
    private Type attractionType;

    public Type getAttractionType() {
        return attractionType;
    }

    public void setAttractionType(Type attractionType) {
        this.attractionType = attractionType;
    }


    public String getAttractionDescription() {
        return attractionDescription;
    }

    public void setAttractionDescription(String attractionDescription) {
        this.attractionDescription = attractionDescription;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


}
