package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import hr.tis.academy.sightseeingapp.model.Attraction;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, String> {
    @Query("SELECT a FROM Attraction a WHERE a.location= :location")
    public Attraction fetchByLocation(String location);
}
