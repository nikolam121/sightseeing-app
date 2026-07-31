package hr.tis.academy.sightseeingapp.repository;

import hr.tis.academy.sightseeingapp.model.HttpError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpErrorRepository extends JpaRepository<HttpError, Long> {

}
