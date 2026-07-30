package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.AttractionDetailsDto;
import org.springframework.http.ResponseEntity;

public interface AttractionDetailsService {
    ResponseEntity<AttractionDetailsDto> getAttractionDetailsByLocationAndURLName(String location, String attractionURLName);
}
