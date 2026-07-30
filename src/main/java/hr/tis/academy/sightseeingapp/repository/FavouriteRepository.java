package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    boolean existsByLocationAndAttractionName(String location, String attractionName);
}
