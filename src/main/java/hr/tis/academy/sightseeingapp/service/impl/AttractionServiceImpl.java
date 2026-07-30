package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.service.AttractionService;
import org.springframework.stereotype.Service;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }
    @Override
    public Attraction findByLocation(String location) {
        return null;
    }
}
