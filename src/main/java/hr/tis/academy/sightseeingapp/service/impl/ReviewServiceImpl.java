package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.ReviewDto;
import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import hr.tis.academy.sightseeingapp.model.Review;
import hr.tis.academy.sightseeingapp.repository.AttractionMetadataRepository;
import hr.tis.academy.sightseeingapp.repository.ReviewRepository;
import hr.tis.academy.sightseeingapp.service.ReviewService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AttractionMetadataRepository attractionMetadataRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, AttractionMetadataRepository attractionMetadataRepository) {
        this.reviewRepository = reviewRepository;
        this.attractionMetadataRepository = attractionMetadataRepository;
    }

    @Override
    public ResponseEntity<Void> addReview(ReviewDto request) {
        if (request.location() == null || request.attractionName() == null
                || request.timestamp() == null || request.rating() == null
                || request.reviewText() == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "All fields are required");
            headers.set("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }

        if (request.rating().compareTo(BigDecimal.ONE) < 0
                || request.rating().compareTo(BigDecimal.valueOf(5)) > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Rating must be between 1 and 5");
            headers.set("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }

        AttractionMetadata metadata = attractionMetadataRepository.findByLocation(request.location());

        Attraction attraction = null;
        for (Attraction a : metadata.getAttractions()) {
            if (a.getName().equals(request.attractionName())) {
                attraction = a;
                break;
            }
        }


        if (attraction == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Attraction not found: " + request.attractionName() + " in " + request.location());
            headers.set("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        Review review = new Review();
        review.setAttraction(attraction);
        review.setTimestamp(request.timestamp());
        review.setRating(request.rating());
        review.setReviewText(request.reviewText());

        reviewRepository.save(review);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}