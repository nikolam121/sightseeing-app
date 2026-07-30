package hr.tis.academy.sightseeingapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ATTRACTION_METADATA", schema = "SIGHTSEEING")
public class AttractionMetadata {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTRACTION_ID")
    List<Attraction> attractions;


}
