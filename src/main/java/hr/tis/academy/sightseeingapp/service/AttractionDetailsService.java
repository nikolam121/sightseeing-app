package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.AttractionDetailsDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface AttractionDetailsService {
    ResponseEntity<AttractionDetailsDto> getAttractionDetailsByLocationAndURLName(String location,
                                                                                  String attractionURLName,
                                                                                  boolean excludeReviews,
                                                                                  LocalDateTime reviewsFrom,
                                                                                  LocalDateTime reviewsTo);
}
