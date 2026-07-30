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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AttractionDetailsServiceImpl implements AttractionDetailsService {

    private final AttractionMetadataRepository attractionMetadataRepository;
    private final ReviewRepository reviewRepository;
    private final AttractionMapper attractionMapper;
    private final ReviewMapper reviewMapper;

    public AttractionDetailsServiceImpl(AttractionMetadataRepository attractionMetadataRepository, ReviewRepository reviewRepository, AttractionMapper attractionMapper, ReviewMapper reviewMapper) {
        this.attractionMetadataRepository = attractionMetadataRepository;
        this.reviewRepository = reviewRepository;
        this.attractionMapper = attractionMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ResponseEntity<AttractionDetailsDto> getAttractionDetailsByLocationAndURLName(String location, String attractionURLName) {
        AttractionMetadata attractionMetadata = attractionMetadataRepository.findByLocation(location);
        List<Attraction> attractionList = attractionMetadata.getAttractions();


        List<Review> reviewList;
        AttractionDetailsDto attractionDetailsDto = null;

        if (!attractionList.isEmpty()) {
            for (Attraction a : attractionList) {
                if (a.getUrlName().equals(attractionURLName)) {
                    reviewList = reviewRepository.findByAttraction(a);
                    BigDecimal sum = reviewList.stream().map(Review::getRating).reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal avg = sum.divide(BigDecimal.valueOf(reviewList.size()));
                    attractionDetailsDto = new AttractionDetailsDto(a.getName(), a.getDescription(), a.getType(), avg, reviewMapper.toDto(reviewList));
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(attractionDetailsDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
