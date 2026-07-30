package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.ReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ReviewService {
    ResponseEntity<Void> addReview(ReviewDto request);
}