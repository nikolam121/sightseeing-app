package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAttraction(Attraction attraction);
}