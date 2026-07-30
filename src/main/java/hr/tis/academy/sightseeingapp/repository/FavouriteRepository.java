package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.Favourite;
import hr.tis.academy.sightseeingapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    boolean existsByLocationAndAttractionName(Location location, String attractionName);
    List<Favourite> findAllByUserId(Long userId);
}
