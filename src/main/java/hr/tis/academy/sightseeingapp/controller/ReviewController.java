package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.dto.ReviewDto;
import hr.tis.academy.sightseeingapp.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ReviewController", description = "Review management")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "post a review")
    @PostMapping("/attraction/review")
    public ResponseEntity<Void> addReview(@RequestBody ReviewDto request) {
        return reviewService.addReview(request);
    }
}