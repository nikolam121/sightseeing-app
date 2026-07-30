package hr.tis.academy.sightseeingapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LOCATION", schema = "SIGHTSEEING")
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Location() {

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
