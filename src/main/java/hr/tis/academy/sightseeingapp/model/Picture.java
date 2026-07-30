package hr.tis.academy.sightseeingapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PICTURE", schema = "SIGHTSEEING")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ATTRACTION_ID")
    private Attraction attraction;

    @Lob
    @Column(name = "DATA")
    private byte[] data;

    @Column
    private String contentType;

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}