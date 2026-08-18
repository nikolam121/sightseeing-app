package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.ReviewDto;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    ResponseEntity<Void> addReview(ReviewDto request);
}
