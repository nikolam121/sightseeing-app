package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import hr.tis.academy.sightseeingapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionMetadataRepository extends JpaRepository<AttractionMetadata, Long> {
    @Query("SELECT am FROM AttractionMetadata am " +
            "WHERE am.location.name LIKE :name")
    AttractionMetadata findByLocation(@Param("name") String name);
}
