package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Attraction findByName(String name);
    boolean existsByName(String name);


}
