package hr.tis.academy.sightseeingapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "LOCATION", schema = "SIGHTSEEING")
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String locationName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTRACTION_ID")
    private List<Attraction> attraction;

    public Location(Long id, String locationName) {
        this.id = id;
        this.locationName = locationName;
    }

    public Location() {

    }


    public Long getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
