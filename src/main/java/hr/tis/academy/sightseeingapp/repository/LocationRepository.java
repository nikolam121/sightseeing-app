package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    boolean existsByName(String name);
}
