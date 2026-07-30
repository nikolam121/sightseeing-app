package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.repository.AttractionRepository;
import hr.tis.academy.sightseeingapp.service.AttractionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }
    @Override
    public List<Attraction> findByLocation(String location) {
        return null;
    }
}
