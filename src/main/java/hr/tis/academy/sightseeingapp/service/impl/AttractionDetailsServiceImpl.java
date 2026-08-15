package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.AttractionDetailsDto;
import hr.tis.academy.sightseeingapp.mapper.AttractionMapper;
import hr.tis.academy.sightseeingapp.mapper.ReviewMapper;
import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import hr.tis.academy.sightseeingapp.model.Review;
import hr.tis.academy.sightseeingapp.repository.AttractionMetadataRepository;
import hr.tis.academy.sightseeingapp.repository.ReviewRepository;
import hr.tis.academy.sightseeingapp.service.AttractionDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttractionDetailsServiceImpl implements AttractionDetailsService {

    private static final int RATING_SCALE = 2;

    private final AttractionMetadataRepository attractionMetadataRepository;
    private final ReviewRepository reviewRepository;
    private final AttractionMapper attractionMapper;
    private final ReviewMapper reviewMapper;

    public AttractionDetailsServiceImpl(AttractionMetadataRepository attractionMetadataRepository,
                                        ReviewRepository reviewRepository,
                                        AttractionMapper attractionMapper,
                                        ReviewMapper reviewMapper) {
        this.attractionMetadataRepository = attractionMetadataRepository;
        this.reviewRepository = reviewRepository;
        this.attractionMapper = attractionMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ResponseEntity<AttractionDetailsDto> getAttractionDetailsByLocationAndURLName(String location,
                                                                                        String attractionURLName,
                                                                                        boolean excludeReviews,
                                                                                        LocalDateTime reviewsFrom,
                                                                                        LocalDateTime reviewsTo) {
        AttractionMetadata attractionMetadata = attractionMetadataRepository.findByLocation(location);

        if (attractionMetadata == null || attractionMetadata.getAttractions() == null) {
            return notFound("Location does not exist: " + location);
        }

        Optional<Attraction> match = attractionMetadata.getAttractions().stream()
                .filter(attraction -> attraction.getName() != null)
                .filter(attraction -> attraction.getUrlName().equals(attractionURLName))
                .findFirst();

        if (match.isEmpty()) {
            return notFound("Attraction does not exist: " + attractionURLName);
        }

        Attraction attraction = match.get();
        List<Review> reviews = filterReviews(reviewRepository.findByAttraction(attraction), reviewsFrom, reviewsTo);

        AttractionDetailsDto attractionDetailsDto = new AttractionDetailsDto(
                attraction.getName(),
                attraction.getDescription(),
                attraction.getType(),
                averageRating(reviews),
                excludeReviews ? List.of() : reviewMapper.toDto(reviews)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("timestamp", LocalTime.now().toString());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(attractionDetailsDto);
    }

    private List<Review> filterReviews(List<Review> reviews, LocalDateTime from, LocalDateTime to) {
        return reviews.stream()
                .filter(review -> review.getTimestamp() != null)
                .filter(review -> from == null || !review.getTimestamp().isBefore(from))
                .filter(review -> to == null || !review.getTimestamp().isAfter(to))
                .toList();
    }

    private BigDecimal averageRating(List<Review> reviews) {
        if (reviews.isEmpty()) {
            return BigDecimal.ZERO.setScale(RATING_SCALE, RoundingMode.HALF_UP);
        }

        BigDecimal sum = reviews.stream()
                .map(Review::getRating)
                .filter(rating -> rating != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(BigDecimal.valueOf(reviews.size()), RATING_SCALE, RoundingMode.HALF_UP);
    }

    private ResponseEntity<AttractionDetailsDto> notFound(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", message);
        headers.add("timestamp", LocalTime.now().toString());
        headers.add("uuid", UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
    }
}
