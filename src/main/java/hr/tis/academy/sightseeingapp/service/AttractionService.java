package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.model.Attraction;

public interface AttractionService {
    Attraction findByLocation(String location);
}
